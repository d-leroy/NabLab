// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.instruction;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.ProbeNode;
import com.oracle.truffle.api.instrumentation.InstrumentableNode.WrapperNode;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

@GeneratedBy(NablaInstructionBlockNode.class)
final class NablaInstructionBlockNodeWrapper extends NablaInstructionBlockNode implements WrapperNode {

    @Child private NablaInstructionBlockNode delegateNode;
    @Child private ProbeNode probeNode;

    NablaInstructionBlockNodeWrapper(NablaInstructionBlockNode delegateNode, ProbeNode probeNode) {
        this.delegateNode = delegateNode;
        this.probeNode = probeNode;
    }

    @Override
    public NablaInstructionBlockNode getDelegateNode() {
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
    public boolean executeBoolean(VirtualFrame arg0, NablaInstructionNode arg1, int arg2, int arg3) throws UnexpectedResultException {
        boolean returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(arg0);
                    returnValue = delegateNode.executeBoolean(arg0, arg1, arg2, arg3);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(arg0, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof Boolean) {
                    returnValue = (boolean) result;
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
    public byte executeByte(VirtualFrame arg0, NablaInstructionNode arg1, int arg2, int arg3) throws UnexpectedResultException {
        byte returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(arg0);
                    returnValue = delegateNode.executeByte(arg0, arg1, arg2, arg3);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(arg0, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof Byte) {
                    returnValue = (byte) result;
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
    public char executeChar(VirtualFrame arg0, NablaInstructionNode arg1, int arg2, int arg3) throws UnexpectedResultException {
        char returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(arg0);
                    returnValue = delegateNode.executeChar(arg0, arg1, arg2, arg3);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(arg0, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof Character) {
                    returnValue = (char) result;
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
    public double executeDouble(VirtualFrame arg0, NablaInstructionNode arg1, int arg2, int arg3) throws UnexpectedResultException {
        double returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(arg0);
                    returnValue = delegateNode.executeDouble(arg0, arg1, arg2, arg3);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(arg0, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof Double) {
                    returnValue = (double) result;
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
    public float executeFloat(VirtualFrame arg0, NablaInstructionNode arg1, int arg2, int arg3) throws UnexpectedResultException {
        float returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(arg0);
                    returnValue = delegateNode.executeFloat(arg0, arg1, arg2, arg3);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(arg0, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof Float) {
                    returnValue = (float) result;
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
    public Object executeGeneric(VirtualFrame frame, NablaInstructionNode node, int index, int argument) {
        Object returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                probeNode.onEnter(frame);
                returnValue = delegateNode.executeGeneric(frame, node, index, argument);
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
    public int executeInt(VirtualFrame arg0, NablaInstructionNode arg1, int arg2, int arg3) throws UnexpectedResultException {
        int returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(arg0);
                    returnValue = delegateNode.executeInt(arg0, arg1, arg2, arg3);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(arg0, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof Integer) {
                    returnValue = (int) result;
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
    public long executeLong(VirtualFrame arg0, NablaInstructionNode arg1, int arg2, int arg3) throws UnexpectedResultException {
        long returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(arg0);
                    returnValue = delegateNode.executeLong(arg0, arg1, arg2, arg3);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(arg0, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof Long) {
                    returnValue = (long) result;
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
    public short executeShort(VirtualFrame arg0, NablaInstructionNode arg1, int arg2, int arg3) throws UnexpectedResultException {
        short returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                try {
                    probeNode.onEnter(arg0);
                    returnValue = delegateNode.executeShort(arg0, arg1, arg2, arg3);
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, returnValue);
                    break;
                } catch (UnexpectedResultException e) {
                    wasOnReturnExecuted = true;
                    probeNode.onReturnValue(arg0, e.getResult());
                    throw e;
                }
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(arg0, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                }
                if (result instanceof Short) {
                    returnValue = (short) result;
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
    public void executeVoid(VirtualFrame frame, NablaInstructionNode node, int index, int argument) {
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                probeNode.onEnter(frame);
                delegateNode.executeVoid(frame, node, index, argument);
                wasOnReturnExecuted = true;
                probeNode.onReturnValue(frame, null);
                break;
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                } else if (result != null) {
                    break;
                }
                throw t;
            }
        }
    }

}
