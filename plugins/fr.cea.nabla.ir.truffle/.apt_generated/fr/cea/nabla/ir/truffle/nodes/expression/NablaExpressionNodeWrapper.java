// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.ProbeNode;
import com.oracle.truffle.api.instrumentation.InstrumentableNode.WrapperNode;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV0Bool;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV0Real;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NV1Real;
import fr.cea.nabla.ir.truffle.values.NV2Int;
import fr.cea.nabla.ir.truffle.values.NV2Real;
import fr.cea.nabla.ir.truffle.values.NV3Int;
import fr.cea.nabla.ir.truffle.values.NV3Real;
import fr.cea.nabla.ir.truffle.values.NV4Int;
import fr.cea.nabla.ir.truffle.values.NV4Real;

@GeneratedBy(NablaExpressionNode.class)
final class NablaExpressionNodeWrapper extends NablaExpressionNode implements WrapperNode {

    @Child private NablaExpressionNode delegateNode;
    @Child private ProbeNode probeNode;

    NablaExpressionNodeWrapper(NablaExpressionNode delegateNode, ProbeNode probeNode) {
        this.delegateNode = delegateNode;
        this.probeNode = probeNode;
    }

    @Override
    public NablaExpressionNode getDelegateNode() {
        return delegateNode;
    }

    @Override
    public ProbeNode getProbeNode() {
        return probeNode;
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.NONE;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        Object returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                probeNode.onEnter(frame);
                returnValue = delegateNode.executeGeneric(frame);
                wasOnReturnExecuted = true;
                probeNode.onReturnValue(frame, returnValue);
                break;
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                } else if (result != null) {
                    returnValue = result;
                    break;
                }
                throw t;
            }
        }
        return returnValue;
    }

    @Override
    public NV0Bool executeNV0Bool(VirtualFrame frame) throws UnexpectedResultException {
        NV0Bool returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(frame);
                    returnValue = delegateNode.executeNV0Bool(frame);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof NV0Bool) {
                    returnValue = (NV0Bool) result;
                    break;
                } else if (result != null) {
                    throw new UnexpectedResultException(result);
                }
                throw t;
            }
        }
        return returnValue;
    }

    @Override
    public NV0Int executeNV0Int(VirtualFrame frame) throws UnexpectedResultException {
        NV0Int returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(frame);
                    returnValue = delegateNode.executeNV0Int(frame);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof NV0Int) {
                    returnValue = (NV0Int) result;
                    break;
                } else if (result != null) {
                    throw new UnexpectedResultException(result);
                }
                throw t;
            }
        }
        return returnValue;
    }

    @Override
    public NV0Real executeNV0Real(VirtualFrame frame) throws UnexpectedResultException {
        NV0Real returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(frame);
                    returnValue = delegateNode.executeNV0Real(frame);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof NV0Real) {
                    returnValue = (NV0Real) result;
                    break;
                } else if (result != null) {
                    throw new UnexpectedResultException(result);
                }
                throw t;
            }
        }
        return returnValue;
    }

    @Override
    public NV1Int executeNV1Int(VirtualFrame frame) throws UnexpectedResultException {
        NV1Int returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(frame);
                    returnValue = delegateNode.executeNV1Int(frame);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof NV1Int) {
                    returnValue = (NV1Int) result;
                    break;
                } else if (result != null) {
                    throw new UnexpectedResultException(result);
                }
                throw t;
            }
        }
        return returnValue;
    }

    @Override
    public NV1Real executeNV1Real(VirtualFrame frame) throws UnexpectedResultException {
        NV1Real returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(frame);
                    returnValue = delegateNode.executeNV1Real(frame);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof NV1Real) {
                    returnValue = (NV1Real) result;
                    break;
                } else if (result != null) {
                    throw new UnexpectedResultException(result);
                }
                throw t;
            }
        }
        return returnValue;
    }

    @Override
    public NV2Int executeNV2Int(VirtualFrame frame) throws UnexpectedResultException {
        NV2Int returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(frame);
                    returnValue = delegateNode.executeNV2Int(frame);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof NV2Int) {
                    returnValue = (NV2Int) result;
                    break;
                } else if (result != null) {
                    throw new UnexpectedResultException(result);
                }
                throw t;
            }
        }
        return returnValue;
    }

    @Override
    public NV2Real executeNV2Real(VirtualFrame frame) throws UnexpectedResultException {
        NV2Real returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(frame);
                    returnValue = delegateNode.executeNV2Real(frame);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof NV2Real) {
                    returnValue = (NV2Real) result;
                    break;
                } else if (result != null) {
                    throw new UnexpectedResultException(result);
                }
                throw t;
            }
        }
        return returnValue;
    }

    @Override
    public NV3Int executeNV3Int(VirtualFrame frame) throws UnexpectedResultException {
        NV3Int returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(frame);
                    returnValue = delegateNode.executeNV3Int(frame);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof NV3Int) {
                    returnValue = (NV3Int) result;
                    break;
                } else if (result != null) {
                    throw new UnexpectedResultException(result);
                }
                throw t;
            }
        }
        return returnValue;
    }

    @Override
    public NV3Real executeNV3Real(VirtualFrame frame) throws UnexpectedResultException {
        NV3Real returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(frame);
                    returnValue = delegateNode.executeNV3Real(frame);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof NV3Real) {
                    returnValue = (NV3Real) result;
                    break;
                } else if (result != null) {
                    throw new UnexpectedResultException(result);
                }
                throw t;
            }
        }
        return returnValue;
    }

    @Override
    public NV4Int executeNV4Int(VirtualFrame frame) throws UnexpectedResultException {
        NV4Int returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(frame);
                    returnValue = delegateNode.executeNV4Int(frame);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof NV4Int) {
                    returnValue = (NV4Int) result;
                    break;
                } else if (result != null) {
                    throw new UnexpectedResultException(result);
                }
                throw t;
            }
        }
        return returnValue;
    }

    @Override
    public NV4Real executeNV4Real(VirtualFrame frame) throws UnexpectedResultException {
        NV4Real returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(frame);
                    returnValue = delegateNode.executeNV4Real(frame);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(frame, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof NV4Real) {
                    returnValue = (NV4Real) result;
                    break;
                } else if (result != null) {
                    throw new UnexpectedResultException(result);
                }
                throw t;
            }
        }
        return returnValue;
    }

}
