package fr.cea.nabla.ir.truffle.nodes.expression.binary;

import com.oracle.truffle.api.dsl.NodeChild;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;

@NodeChild("leftNode")
@NodeChild("rightNode")
public abstract class NablaBinaryExpressionNode extends NablaExpressionNode {}
