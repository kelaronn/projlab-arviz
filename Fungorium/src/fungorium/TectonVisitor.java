package fungorium;

public interface TectonVisitor {
    void visit(NarrowTecton t);

    void visit(WideTecton t);

    void visit(BarrenTecton t);

    void visit(WeakTecton t);

    void visit(VitalTecton t);
}
