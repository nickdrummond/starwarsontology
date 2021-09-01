# Advantages of modelling Star Wars in OWL

## Open World

This is an ongoing story with gaps being filled all the time.

It's good to have statements about what we know to be true but allowing all sorts of additional facts to become true

eg. Darth Vader sameAs Anakin Skywalker

## Inference

Using property hierarchy to infer relationship neighbourhoods

    knew value Anakin_Skywalker
    knew some (knew value Anakin_Skywalker)

## Graphs

Any property can be navigated as a graph

eg Event sequence (after/before) and containment (during)

![TROS timeline](events-TROS.png)

## Easy transitivity

Containment also very useful for locations

eg. Who was murdered in the Outer Rim?

Han killed Greedo in the Cantina in Mos Eisely on Tatooine in Outer Rim

eg. Shmi is related to Kylo Ren.

## Specificity

We have ways of modelling that can leave out unknown details or keep our level of commitment low.

People born somewhere in a range of years

Classification allows low-specificity
eg Role = Fighter or Trooper or Stormtrooper

eg No need to name everything (parents etc) - we can talk about Ezra's parents without having to name them.

## Modularity

The ontology is modularised into each episode/series.

As the ontology gets larger, modules help with focus, although there is a tradeoff with the fluidity of developing one ontology

## Referencing

We can reference any number of sources to our entities/assertions - eg Wookipedia

We could reference images/videos - many ontology tools recognise common formats eg jpg
But, we would need to deal with permissions/hot linking etc so this is out of scope currently.