// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.constant.NablaRealConstantNode;

@GeneratedBy(NablaRealConstantNode.class)
public final class NablaRealConstantNodeGen extends NablaRealConstantNode {

    private NablaRealConstantNodeGen(double value) {
        super(value);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV0Real(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaRealConstantNode create(double value) {
        return new NablaRealConstantNodeGen(value);
    }

}
