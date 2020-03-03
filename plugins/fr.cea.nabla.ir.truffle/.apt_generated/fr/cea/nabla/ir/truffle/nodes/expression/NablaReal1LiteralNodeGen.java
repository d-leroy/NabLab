// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReal1LiteralNode;

@GeneratedBy(NablaReal1LiteralNode.class)
public final class NablaReal1LiteralNodeGen extends NablaReal1LiteralNode {

    private NablaReal1LiteralNodeGen(NablaExpressionNode[] values) {
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

    public static NablaReal1LiteralNode create(NablaExpressionNode[] values) {
        return new NablaReal1LiteralNodeGen(values);
    }

}
