package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {

    List<Simulation> simulations;
    List<Thread> threads = new ArrayList<>();

    public SimulationEngine(List<Simulation> simulations) {

        this.simulations = simulations;
    }

    public void runSync()
    {
        for (Simulation sym : simulations)
        {
            sym.run();
        }
    }

    public void runAsync()
    {
        for (Simulation sym : simulations) {
            Thread thread = new Thread(sym);
            threads.add(thread);
            thread.start();
        }
    }

    public void awaitSymulationsEnd()
    {
        for (Thread thread : threads)
        {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void runAsyncInThreadPool()
    {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for(Simulation sym : simulations)
        {
            executorService.submit(sym);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
