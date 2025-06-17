package fungorium;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Point;
import java.awt.Rectangle;

public class DrawVisitor implements TectonVisitor {
    private JLabel label;

    public JLabel getLabel() {
        label.setBounds(new Rectangle(new Point(75, 5), label.getPreferredSize()));
        return label;
    }

    @Override
    public void visit(NarrowTecton t) {
        label = new JLabel(new ImageIcon("fungorium/NarrowTecton.png"));

    }

    @Override
    public void visit(WideTecton t) {
        label = new JLabel(new ImageIcon("fungorium/WideTecton.png"));
    }

    @Override
    public void visit(BarrenTecton t) {
        label = new JLabel(new ImageIcon("fungorium/BarrenTecton.png"));
    }

    @Override
    public void visit(WeakTecton t) {
        label = new JLabel(new ImageIcon("fungorium/WeakTecton.png"));
    }

    @Override
    public void visit(VitalTecton t) {
        label = new JLabel(new ImageIcon("fungorium/VitalTecton.png"));
    }
}
