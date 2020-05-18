// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.constant.NablaIntConstantNode;

@GeneratedBy(NablaIntConstantNode.class)
public final class NablaIntConstantNodeGen extends NablaIntConstantNode {

    private NablaIntConstantNodeGen(int value) {
        super(value);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV0Int(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaIntConstantNode create(int value) {
        return new NablaIntConstantNodeGen(value);
    }

}
