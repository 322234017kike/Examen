package Exam;

public class Tanque {

    private float almacenamiento;
    private float combustible;

    // Constructores
    public Tanque(float almacenamiento) {
        this(almacenamiento, 0);
    }

    public Tanque(float almacenamiento, float combustible) {
        if (!(almacenamiento > 0)) {
            throw new IllegalArgumentException("El almacenamiento debe ser mayor que cero.");
        }
        this.almacenamiento = almacenamiento;
        // Asigna combustible inicial asegurando que no sea negativo ni supere la capacidad
        this.combustible = (combustible > 0) ? Math.min(combustible, almacenamiento) : 0;
    }

    // Métodos de llenado
    public void llenarTanque() {
        this.combustible = this.almacenamiento;
    }

    public void llenarTanque(float cantidad) {
        if (cantidad <= 0) return;
        this.combustible = Math.min(this.combustible + cantidad, this.almacenamiento);
    }

    // Métodos de vaciado
    public void vaciarTanque() {
        this.combustible = 0;
    }

    public void vaciarTanque(float cantidad) {
        if (cantidad <= 0) return;
        this.combustible = Math.max(this.combustible - cantidad, 0);
    }

    // Métodos de consulta / Getters
    public float consultarCombustible() {
        return this.combustible;
    }

    public float consultarPorcentaje() {
        return (this.combustible / this.almacenamiento) * 100f;
    }

    public float getAlmacenamiento() {
        return this.almacenamiento;
    }
}
