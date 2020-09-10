// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaReal1Node;

@GeneratedBy(NablaReal1Node.class)
public final class NablaReal1NodeGen extends NablaReal1Node {

    private NablaReal1NodeGen(NablaExpressionNode[] values) {
        super(values);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV1Real(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaReal1Node create(NablaExpressionNode[] values) {
        return new NablaReal1NodeGen(values);
    }

}
