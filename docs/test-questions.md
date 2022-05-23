[docs](index.md) |
[benefits](benefits.md) |
[modules](modularisation.md) |
[events](events.md) |
[modelling principles](modelling-principles.md) |
test questions |
[performance](performance.md) |
[tools](tools.md)

# Test Questions

What can we ask of the Star Wars Universe we have modelled?

We have the following mechanisms available to us:
* Asserted facts
* Inferred facts
* Usage (includes backward references)
* DL query
* SparQL
* Sqwrl?
* Protege tooling
    * Individuals matrix
    * Ontograf


## Places

### Transitivity on locations

    City and locatedIn value Outer_Rim

[result](https://star-wars-ontology.herokuapp.com/dlquery/?expression=City+and+locatedIn+value+Outer_Rim&syntax=man)
 = [```Cloud City```](http://localhost:8080/individuals/723710809/),
[```Canto Bight```](http://localhost:8080/individuals/-1408061252/) etc

### What events happened in a given location?

    Event and locatedIn value Naboo

[result](https://star-wars-ontology.herokuapp.com/dlquery/?expression=Event+and+%28locatedIn+value+Naboo%29&syntax=man)

### Events that happened in a mountainous place

    Event and (locatedIn some (hasTerrain some Mountains))

[result](https://star-wars-ontology.herokuapp.com/dlquery/?expression=Event+and+%28locatedIn+some+%28hasTerrain+some+Mountains%29%29&syntax=man)
 = [```B-Wing_test_flight```](http://localhost:8080/individuals/-533932360/),
[```First_Battle_of_Geonosis```](http://localhost:8080/individuals/-1153681569/) etc

### What events was Lando involved in?

    Event and participant value Lando_Calrissian

[result](https://star-wars-ontology.herokuapp.com/dlquery/?expression=Event+and+%28participant+value+Lando_Calrissian%29&syntax=man)

Or more complete if we also capture sub-events:

    Event and (
        (participant value Ahsoka_Tano) or
        (included some (participant value Ahsoka_Tano)))

[result](https://star-wars-ontology.herokuapp.com/dlquery/?expression=Event+and+%28%28participant+value+Ahsoka_Tano%29+or+%28included+some+%28participant+value+Ahsoka_Tano%29%29%29&syntax=man)

## Where has Ezra been?
We can ask the locations of events at which Ezra (or the Spectres) were present:

    locationOf some (Event and participant some {Ezra_Bridger, Spectres})

Or planets that Ahsoka has been to:

    Planet and locationOf some (Event and participant value Ahsoka_Tano)

nb. see [performance](performance.md) issues around making ```visited``` property chain

## Who is from the Outer Rim?
Get all beings from the [```Outer Rim```](https://star-wars-ontology.herokuapp.com/individuals/-1386770186/):

    originallyFrom some (Place and locatedIn value Outer_Rim)

[```Anakin```](https://star-wars-ontology.herokuapp.com/individuals/124477048/)
is in the results as he is from  [```Mos Espa```](https://star-wars-ontology.herokuapp.com/individuals/-1084757583/)


## Membership of Organisations?
We have a mixture of asserted membership and roles in organisations.

This mixture is handled by property chain on [```memberOf```](https://star-wars-ontology.herokuapp.com/objectproperties/-1602556939/):

    hadRole o inOrganisation -> memberOf    

    memberOf value Rebel_Alliance

Includes [```Amilyn_Holdo```](https://star-wars-ontology.herokuapp.com/individuals/1514973977/), who is not directly asserted to be a member
Also includes [```Ezra_Bridger```](https://star-wars-ontology.herokuapp.com/individuals/-1105472430/), who is a member of [```Spectres```](https://star-wars-ontology.herokuapp.com/individuals/1273958379/)

If we want to ask about who holds a specific role in an org, the following is incomplete - should include ```3-9```:

    hadRole some (Officer and inOrganisation value Galactic_Empire)

We need to query for the org and its parts:

    hadRole some (Officer and( inOrganisation some ({Galactic_Empire} or memberOf value Galactic_Empire )))

It makes sense to distinguish, as not all roles are equivalent when looking at the whole:
A leader of a unit does not make a leader of the army.

## Born/died before the Battle of Yavin
Can we do this relative to an event? 
ie more granular so we catch Scarif (the same year)

    diedIn some ( year some xsd:int[< 0] )

[```sometimeBefore```](https://star-wars-ontology.herokuapp.com/objectproperties/-1091741052/)
is transitive but we don't want to hold a complete timeline for all the story fragments,
so we do have to use year aswell

    diedIn some (((year some xsd:int[< 0] ) or ((sometimeBefore value Battle_of_Yavin) or (during some (sometimeBefore value Battle_of_Yavin)))))

## Who is related to Anakin Skywalker?

    relatedTo value Anakin_Skywalker

- Should include [```Ben_Solo```](https://star-wars-ontology.herokuapp.com/individuals/791330359/).
- Also includes [```Han_Solo```](https://star-wars-ontology.herokuapp.com/individuals/-891756947/) (as Ben's Father) as related is transitive. Should it?
- [```Rex```](https://star-wars-ontology.herokuapp.com/individuals/-953035159/)
is related to [```Jango Fett```](https://star-wars-ontology.herokuapp.com/individuals/1082292387/)

## Who died in a certain place or by a given hand?

    killedIn some (locatedIn value Death_Star_1)

Included
[```Obi-Wan_Kenobi```](https://star-wars-ontology.herokuapp.com/individuals/430816088/) and
[```Biggs_Darklighter```](https://star-wars-ontology.herokuapp.com/individuals/-1217341474/)

    killedIn some (Fight and participant value Darth_Vader)

Included 
[```Raymus_Antilles```](https://star-wars-ontology.herokuapp.com/individuals/-587033198/)

## Who was in a fight in which StormTroopers were killed?

    participatedIn some (Fight and killingOf some (hadRole some StormTrooper))

Too many to mention

## Any event in which a Star Destroyer was used

    (participant some Star_Destroyer) or included some (participant some Star_Destroyer)

## Any event in which explosives were used

    (used some Explosive) or included some (used some Explosive)

## People meeting/knowing each other

Can we query for the people someone has met?
Probably not as just being at the same event or a member of a group doesn't mean they've met.

It  depends on the number of people in that event/group
  - ie not everyone in the Empire knows each other
  - not everyone at the Battle of Scariff knows each other

People that participated in the same Events:

    participatedIn some (participant value Asajj_Ventress)