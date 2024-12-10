package pt.isec.pd.servidores.controladores;

public class ControladorThreads extends java.lang.Thread{

    int threadNumber;
    public ControladorThreads(int num){threadNumber = num;}

    public void run() //teste do professor...
    {
        System.out.println ("I am thread number " + threadNumber);
        try {
        // SLEEP FOR FIVE THOUSAND MILLISECONDS (5 SECS),
        // TO SIMULATE WORK BEING DONE
            Thread.sleep(5000);
        }catch (InterruptedException e) {}
        System.out.println (threadNumber + " is finished!");
    }

}
