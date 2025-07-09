package com.mycompany.joshiro;

public class Juego {
    
    public class Jugador {
       
        private String nombre;
        private int salud;
        private int ataque;
        private int puntaje;

        public Jugador(String nombre) {
            this.nombre = nombre;
            this.salud = 100;  
            this.ataque = 10;  
            this.puntaje = 0;   
        }

        public void atacar(Enemigo e) {
            if (e != null && e.estaVivo()) {
               
                int dano = this.ataque;  
                System.out.println(this.nombre + " ataca a " + e.getTipo() + " por " + dano + " de daño.");
                e.recibirDano(dano);  
                this.puntaje += 10;
            }
        }

        public void recibirDano(int cantidad) {
            this.salud -= cantidad;
            if (this.salud < 0) {
                this.salud = 0;  
            }
            System.out.println(this.nombre + " ha recibido " + cantidad + " de daño. Salud restante: " + this.salud);
        }

        public boolean estaVivo() {
            return this.salud > 0;
        }

        public void usarMejora(Mejora m) {
            if (m != null) {
                if (m.getTipo().equals("Salud")) {
                    this.salud += m.getValor();
                    System.out.println(this.nombre + " ha recibido una mejora de salud. Salud actual: " + this.salud);
                } else if (m.getTipo().equals("Ataque")) {
                    this.ataque += m.getValor();
                    System.out.println(this.nombre + " ha recibido una mejora de ataque. Ataque actual: " + this.ataque);
                }
            }
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getSalud() {
            return salud;
        }

        public void setSalud(int salud) {
            this.salud = salud;
        }

        public int getAtaque() {
            return ataque;
        }

        public void setAtaque(int ataque) {
            this.ataque = ataque;
        }

        public int getPuntaje() {
            return puntaje;
        }

        public void setPuntaje(int puntaje) {
            this.puntaje = puntaje;
        }
    }

    public class Enemigo {
        private String tipo;  
        private int salud;    
        private int ataque;   

        public Enemigo(String tipo, int salud, int ataque) {
            this.tipo = tipo;
            this.salud = salud;
            this.ataque = ataque;
        }

        public void atacar(Jugador j) {
            if (this.estaVivo()) {
                System.out.println(this.tipo + " ataca a " + j.getNombre() + " por " + this.ataque + " de daño.");
                j.recibirDano(this.ataque);  
            }
        }

        public void recibirDano(int cantidad) {
            this.salud -= cantidad;
            if (this.salud < 0) {
                this.salud = 0;  
            }
            System.out.println(this.tipo + " ha recibido " + cantidad + " de daño. Salud restante: " + this.salud);
        }

        public boolean estaVivo() {
            return this.salud > 0;  
        }

        public void mostrarEstado() {
            System.out.println("Estado de " + this.tipo + ": Salud = " + this.salud + ", Ataque = " + this.ataque);
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public int getSalud() {
            return salud;
        }

        public void setSalud(int salud) {
            this.salud = salud;
        }

        public int getAtaque() {
            return ataque;
        }

        public void setAtaque(int ataque) {
            this.ataque = ataque;
        }
    }

    public class Mejora {
        private String tipo;  
        private int valor;   

        public Mejora(String tipo, int valor) {
            this.tipo = tipo;
            this.valor = valor;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public int getValor() {
            return valor;
        }

        public void setValor(int valor) {
            this.valor = valor;
        }
    }

    public static void main(String[] args) {
        Juego juego = new Juego();

        Jugador jugador = juego.new Jugador("Jugador1");

        Enemigo enemigo1 = juego.new Enemigo("Nave Alienígena", 50, 15);
        Enemigo enemigo2 = juego.new Enemigo("Nave Espacial", 70, 12);

        enemigo1.mostrarEstado();
        enemigo2.mostrarEstado();

        jugador.atacar(enemigo1);
        jugador.atacar(enemigo2);

        enemigo1.mostrarEstado();
        enemigo2.mostrarEstado();

        Mejora mejoraSalud = new Mejora("Salud", 20);  
        jugador.usarMejora(mejoraSalud);
    }
}
