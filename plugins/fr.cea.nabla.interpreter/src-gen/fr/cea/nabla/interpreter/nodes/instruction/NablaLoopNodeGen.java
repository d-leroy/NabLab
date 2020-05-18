// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.instruction;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.instruction.NablaInstructionNode;
import fr.cea.nabla.interpreter.nodes.instruction.NablaLoopNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV1Int;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaLoopNode.class)
public final class NablaLoopNodeGen extends NablaLoopNode {

    @Child private NablaExpressionNode count_;
    @CompilationFinal private int state_;
    @CompilationFinal private Loop0Data loop0_cache;
    @CompilationFinal private Loop2Data loop2_cache;

    private NablaLoopNodeGen(FrameSlot indexSlot, FrameSlot counterSlot, NablaInstructionNode body, NablaExpressionNode count) {
        super(indexSlot, counterSlot, body);
        this.count_ = count;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object countValue_ = this.count_.executeGeneric(frameValue);
        if ((state & 0b11) != 0 /* is-active doLoop(VirtualFrame, NV0Int, int) || doLoop(VirtualFrame, NV0Int) */ && countValue_ instanceof NV0Int) {
            NV0Int countValue__ = (NV0Int) countValue_;
            if ((state & 0b1) != 0 /* is-active doLoop(VirtualFrame, NV0Int, int) */) {
                Loop0Data s1_ = this.loop0_cache;
                while (s1_ != null) {
                    if ((countValue__.getData() == s1_.cachedCount_)) {
                        return doLoop(frameValue, countValue__, s1_.cachedCount_);
                    }
                    s1_ = s1_.next_;
                }
            }
            if ((state & 0b10) != 0 /* is-active doLoop(VirtualFrame, NV0Int) */) {
                return doLoop(frameValue, countValue__);
            }
        }
        if ((state & 0b1100) != 0 /* is-active doLoop(VirtualFrame, NV1Int, int) || doLoop(VirtualFrame, NV1Int) */ && countValue_ instanceof NV1Int) {
            NV1Int countValue__ = (NV1Int) countValue_;
            if ((state & 0b100) != 0 /* is-active doLoop(VirtualFrame, NV1Int, int) */) {
                Loop2Data s3_ = this.loop2_cache;
                while (s3_ != null) {
                    if ((countValue__.getData().length == s3_.cachedCount_)) {
                        return doLoop(frameValue, countValue__, s3_.cachedCount_);
                    }
                    s3_ = s3_.next_;
                }
            }
            if ((state & 0b1000) != 0 /* is-active doLoop(VirtualFrame, NV1Int) */) {
                return doLoop(frameValue, countValue__);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, countValue_);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue, Object countValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        try {
            if (countValue instanceof NV0Int) {
                NV0Int countValue_ = (NV0Int) countValue;
                int count1_ = 0;
                Loop0Data s1_ = this.loop0_cache;
                if ((state & 0b1) != 0 /* is-active doLoop(VirtualFrame, NV0Int, int) */) {
                    while (s1_ != null) {
                        if ((countValue_.getData() == s1_.cachedCount_)) {
                            break;
                        }
                        s1_ = s1_.next_;
                        count1_++;
                    }
                }
                if (s1_ == null) {
                    {
                        int cachedCount__ = (countValue_.getData());
                        if ((countValue_.getData() == cachedCount__) && count1_ < (3)) {
                            s1_ = new Loop0Data(loop0_cache);
                            s1_.cachedCount_ = cachedCount__;
                            this.loop0_cache = s1_;
                            this.state_ = state = state | 0b1 /* add-active doLoop(VirtualFrame, NV0Int, int) */;
                        }
                    }
                }
                if (s1_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doLoop(frameValue, countValue_, s1_.cachedCount_);
                }
                this.state_ = state = state | 0b10 /* add-active doLoop(VirtualFrame, NV0Int) */;
                lock.unlock();
                hasLock = false;
                return doLoop(frameValue, countValue_);
            }
            if (countValue instanceof NV1Int) {
                NV1Int countValue_ = (NV1Int) countValue;
                int count3_ = 0;
                Loop2Data s3_ = this.loop2_cache;
                if ((state & 0b100) != 0 /* is-active doLoop(VirtualFrame, NV1Int, int) */) {
                    while (s3_ != null) {
                        if ((countValue_.getData().length == s3_.cachedCount_)) {
                            break;
                        }
                        s3_ = s3_.next_;
                        count3_++;
                    }
                }
                if (s3_ == null) {
                    // assert (countValue_.getData().length == s3_.cachedCount_);
                    if (count3_ < (3)) {
                        s3_ = new Loop2Data(loop2_cache);
                        s3_.cachedCount_ = (countValue_.getData().length);
                        this.loop2_cache = s3_;
                        this.state_ = state = state | 0b100 /* add-active doLoop(VirtualFrame, NV1Int, int) */;
                    }
                }
                if (s3_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doLoop(frameValue, countValue_, s3_.cachedCount_);
                }
                this.state_ = state = state | 0b1000 /* add-active doLoop(VirtualFrame, NV1Int) */;
                lock.unlock();
                hasLock = false;
                return doLoop(frameValue, countValue_);
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.count_}, countValue);
        } finally {
            if (hasLock) {
                lock.unlock();
            }
        }
    }

    @Override
    public NodeCost getCost() {
        int state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else if ((state & (state - 1)) == 0 /* is-single-active  */) {
            Loop0Data s1_ = this.loop0_cache;
            Loop2Data s3_ = this.loop2_cache;
            if ((s1_ == null || s1_.next_ == null) && (s3_ == null || s3_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaLoopNode create(FrameSlot indexSlot, FrameSlot counterSlot, NablaInstructionNode body, NablaExpressionNode count) {
        return new NablaLoopNodeGen(indexSlot, counterSlot, body, count);
    }

    @GeneratedBy(NablaLoopNode.class)
    private static final class Loop0Data {

        @CompilationFinal Loop0Data next_;
        @CompilationFinal int cachedCount_;

        Loop0Data(Loop0Data next_) {
            this.next_ = next_;
        }

    }
    @GeneratedBy(NablaLoopNode.class)
    private static final class Loop2Data {

        @CompilationFinal Loop2Data next_;
        @CompilationFinal int cachedCount_;

        Loop2Data(Loop2Data next_) {
            this.next_ = next_;
        }

    }
}
