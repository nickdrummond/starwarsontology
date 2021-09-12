package com.nickd.sw.util;

import junit.extensions.TestSetup;
import junit.framework.Test;
import org.semanticweb.HermiT.Configuration;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

import java.util.Collections;

public class TestHelper extends TestSetup {

    public static String BASE = "http://null.com/star-wars";

    public OWLOntologyManager mngr;
    public OWLOntology ont;
    public OWLDataFactory df;
    public Reasoner r;

    public long timeToLoad;
    public long timeToClassify;


    public TestHelper(Test test, String iri) throws OWLOntologyCreationException {
        super(test);
        mngr = new OWLManager().get();
        mngr.setIRIMappers(Collections.singleton(new TestIRIMapper()));
        mngr.addOntologyLoaderListener(new LoadLogger());
        df = mngr.getOWLDataFactory();

        long start = System.currentTimeMillis();
        ont = mngr.loadOntology(IRI.create(iri));
        timeToLoad = System.currentTimeMillis() - start;
        System.out.println("Loaded in " + timeToLoad + "ms");
    }

    public OWLNamedIndividual ind(String s) {
        return df.getOWLNamedIndividual(BASE + "#" + s);
    }

    public OWLObjectProperty prop(String s) {
        return df.getOWLObjectProperty(BASE + "#" + s);
    }

    public OWLClass cls(String s) {
        return df.getOWLClass(BASE + "#" + s);
    }

    public void classify() {
        Configuration conf = new Configuration();
        long start = System.currentTimeMillis();
        r = new Reasoner(conf, ont);
        timeToClassify = System.currentTimeMillis() - start;
        System.out.println("Classified in " + timeToClassify + "ms");
    }
}
