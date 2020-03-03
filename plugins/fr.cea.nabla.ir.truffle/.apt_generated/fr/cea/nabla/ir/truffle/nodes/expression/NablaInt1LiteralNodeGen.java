// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaInt1LiteralNode;

@GeneratedBy(NablaInt1LiteralNode.class)
public final class NablaInt1LiteralNodeGen extends NablaInt1LiteralNode {

    private NablaInt1LiteralNodeGen(NablaExpressionNode[] values) {
        super(values);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV1Int(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaInt1LiteralNode create(NablaExpressionNode[] values) {
        return new NablaInt1LiteralNodeGen(values);
    }

}
