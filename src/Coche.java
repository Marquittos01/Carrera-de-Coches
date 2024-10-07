import java.util.Random;

public class Coche implements Runnable {
    private String nombre;
    private int distanciaRecorrida;
    private static final int DISTANCIA_META = 100;
    private static boolean ganadorAnunciado = false;
    private static final Object lock = new Object();

    public Coche(String nombre) {
        this.nombre = nombre;
        this.distanciaRecorrida = 0;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (distanciaRecorrida < DISTANCIA_META) {

            int avance = random.nextInt(10) + 1;
            distanciaRecorrida += avance;


            System.out.println(nombre + " ha recorrido: " + distanciaRecorrida + " unidades.");

            if (distanciaRecorrida >= DISTANCIA_META && !ganadorAnunciado) {
                synchronized (lock) {
                    if (!ganadorAnunciado) {
                        ganadorAnunciado = true;
                        System.out.println(nombre + " ha ganado la carrera!");
                    }
                }
            }

            try {
                Thread.sleep(random.nextInt(500) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public class CarreraDeCoches {
        public static void main(String[] args) {

            Thread[] coches = new Thread[5];
            for (int i = 0; i < coches.length; i++) {
                coches[i] = new Thread(new Coche("Coche " + (i + 1)));
                coches[i].start();
            }

            for (Thread coche : coches) {
                try {
                    coche.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("La carrera ha terminado.");
        }
    }

}
