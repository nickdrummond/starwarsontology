# Todo

[back to index](index.md)

* How do we infer that specific people were participants in `Evacuation_of_Garel` - we're using `Spectres` a lot?
  Should we say that all members of an organisation or crew were at that event? What about Battles - Rebel Alliance vs Empire?
  Not all members of a group are a member at that time.

*  Where we use oneofs, there needs to be specified cardinality instead of `some`
   or else the inference does not work - ie a given member cannot be inferred to be related
   BUT - cannot do this with hadMember as it is transitive which makes it non-simple
   Actually, it doesn't work with the Crew in between  
   an example where it does work is `Escape from Death Star` both Solo and Skywalker are inferred participants

* Can we easily query/infer a timeline for a given character?
    * we can easily [query for the events](docs/events.md) using simple DL query, but how do we put them in some order?
    * If we want to get the events and subevents for a character, we either query this or we have
      a subchain (the subchain is great as it brings the named events in to the inferred props but what is the cost?)


    included o participant -> participant


* Check all events have dates and are not orphaned - see Sparql test

* Series
    * Rebels - series 3(22), 4(16)
    * The Clone Wars series 7 series eps(22, 22, 22, 22, 20, 13, 12)
    * Bad Batch - 1 series eps(incomplete)

* Review property "of" - is it even defined properly?

* "visited" only used once - is it useful?

* "healed" also only used once

* "surrenderOf" - anywhere else to use this?

* Can we have ABY/BBY instead of int as custom datatype?


* Changes to base cannot be saved if other ontologies loaded - https://github.com/protegeproject/protege/pull/1025