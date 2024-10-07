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
