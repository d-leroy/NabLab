// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.instruction;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.EncapsulatingNodeReference;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.instruction.NablaInstructionNode;
import fr.cea.nabla.interpreter.nodes.instruction.NablaLoopNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV1IntLibrary;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaLoopNode.class)
public final class NablaLoopNodeGen extends NablaLoopNode {

    private static final LibraryFactory<NV1IntLibrary> N_V1_INT_LIBRARY_ = LibraryFactory.resolve(NV1IntLibrary.class);

    @Child private NablaExpressionNode count_;
    @CompilationFinal private volatile int state_;
    @CompilationFinal private volatile int exclude_;
    @CompilationFinal private Loop0Data loop0_cache;
    @Child private Loop2Data loop2_cache;
    @Child private Loop3Data loop3_cache;
    @Child private Loop4Data loop4_cache;

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
        if ((state & 0b111100) != 0 /* is-active doLoop(VirtualFrame, Object, NV1IntLibrary, int) || doLoop(VirtualFrame, Object, NV1IntLibrary, int) || doLoop(VirtualFrame, Object, NV1IntLibrary) || doLoop(VirtualFrame, Object, NV1IntLibrary) */) {
            if ((state & 0b100) != 0 /* is-active doLoop(VirtualFrame, Object, NV1IntLibrary, int) */) {
                Loop2Data s3_ = this.loop2_cache;
                while (s3_ != null) {
                    if ((s3_.arrays_.accepts(countValue_)) && (s3_.arrays_.isArray(countValue_)) && (s3_.arrays_.length(countValue_) == s3_.cachedCount_)) {
                        return doLoop(frameValue, countValue_, s3_.arrays_, s3_.cachedCount_);
                    }
                    s3_ = s3_.next_;
                }
            }
            if ((state & 0b1000) != 0 /* is-active doLoop(VirtualFrame, Object, NV1IntLibrary, int) */) {
                NV1IntLibrary arrays__ = (N_V1_INT_LIBRARY_.getUncached(countValue_));
                if ((arrays__.isArray(countValue_))) {
                    Loop3Data s4_ = this.loop3_cache;
                    while (s4_ != null) {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            if ((arrays__.length(countValue_) == s4_.cachedCount_)) {
                                return doLoop(frameValue, countValue_, arrays__, s4_.cachedCount_);
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                        s4_ = s4_.next_;
                    }
                }
            }
            if ((state & 0b10000) != 0 /* is-active doLoop(VirtualFrame, Object, NV1IntLibrary) */) {
                Loop4Data s5_ = this.loop4_cache;
                while (s5_ != null) {
                    if ((s5_.arrays_.accepts(countValue_)) && (s5_.arrays_.isArray(countValue_))) {
                        return doLoop(frameValue, countValue_, s5_.arrays_);
                    }
                    s5_ = s5_.next_;
                }
            }
            if ((state & 0b100000) != 0 /* is-active doLoop(VirtualFrame, Object, NV1IntLibrary) */) {
                EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                Node prev_ = encapsulating_.set(this);
                try {
                    {
                        NV1IntLibrary loop5_arrays__ = (N_V1_INT_LIBRARY_.getUncached(countValue_));
                        if ((loop5_arrays__.isArray(countValue_))) {
                            return doLoop(frameValue, countValue_, loop5_arrays__);
                        }
                    }
                } finally {
                    encapsulating_.set(prev_);
                }
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
        int exclude = exclude_;
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
            if (((exclude & 0b1)) == 0 /* is-not-excluded doLoop(VirtualFrame, Object, NV1IntLibrary, int) */) {
                int count3_ = 0;
                Loop2Data s3_ = this.loop2_cache;
                if ((state & 0b100) != 0 /* is-active doLoop(VirtualFrame, Object, NV1IntLibrary, int) */) {
                    while (s3_ != null) {
                        if ((s3_.arrays_.accepts(countValue)) && (s3_.arrays_.isArray(countValue)) && (s3_.arrays_.length(countValue) == s3_.cachedCount_)) {
                            break;
                        }
                        s3_ = s3_.next_;
                        count3_++;
                    }
                }
                if (s3_ == null) {
                    {
                        NV1IntLibrary arrays__ = super.insert((N_V1_INT_LIBRARY_.create(countValue)));
                        // assert (s3_.arrays_.accepts(countValue));
                        if ((arrays__.isArray(countValue))) {
                            int cachedCount__1 = (arrays__.length(countValue));
                            if ((arrays__.length(countValue) == cachedCount__1) && count3_ < (3)) {
                                s3_ = super.insert(new Loop2Data(loop2_cache));
                                s3_.arrays_ = s3_.insertAccessor(arrays__);
                                s3_.cachedCount_ = cachedCount__1;
                                this.loop2_cache = s3_;
                                this.state_ = state = state | 0b100 /* add-active doLoop(VirtualFrame, Object, NV1IntLibrary, int) */;
                            }
                        }
                    }
                }
                if (s3_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doLoop(frameValue, countValue, s3_.arrays_, s3_.cachedCount_);
                }
            }
            {
                NV1IntLibrary arrays__ = null;
                arrays__ = (N_V1_INT_LIBRARY_.getUncached(countValue));
                if ((arrays__.isArray(countValue))) {
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            int count4_ = 0;
                            Loop3Data s4_ = this.loop3_cache;
                            if ((state & 0b1000) != 0 /* is-active doLoop(VirtualFrame, Object, NV1IntLibrary, int) */) {
                                while (s4_ != null) {
                                    if ((arrays__.length(countValue) == s4_.cachedCount_)) {
                                        break;
                                    }
                                    s4_ = s4_.next_;
                                    count4_++;
                                }
                            }
                            if (s4_ == null) {
                                {
                                    int cachedCount__1 = (arrays__.length(countValue));
                                    if ((arrays__.length(countValue) == cachedCount__1) && count4_ < (3)) {
                                        s4_ = super.insert(new Loop3Data(loop3_cache));
                                        s4_.cachedCount_ = cachedCount__1;
                                        this.loop3_cache = s4_;
                                        this.exclude_ = exclude = exclude | 0b1 /* add-excluded doLoop(VirtualFrame, Object, NV1IntLibrary, int) */;
                                        this.loop2_cache = null;
                                        state = state & 0xfffffffb /* remove-active doLoop(VirtualFrame, Object, NV1IntLibrary, int) */;
                                        this.state_ = state = state | 0b1000 /* add-active doLoop(VirtualFrame, Object, NV1IntLibrary, int) */;
                                    }
                                }
                            }
                            if (s4_ != null) {
                                lock.unlock();
                                hasLock = false;
                                return doLoop(frameValue, countValue, arrays__, s4_.cachedCount_);
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
            }
            if (((exclude & 0b10)) == 0 /* is-not-excluded doLoop(VirtualFrame, Object, NV1IntLibrary) */) {
                int count5_ = 0;
                Loop4Data s5_ = this.loop4_cache;
                if ((state & 0b10000) != 0 /* is-active doLoop(VirtualFrame, Object, NV1IntLibrary) */) {
                    while (s5_ != null) {
                        if ((s5_.arrays_.accepts(countValue)) && (s5_.arrays_.isArray(countValue))) {
                            break;
                        }
                        s5_ = s5_.next_;
                        count5_++;
                    }
                }
                if (s5_ == null) {
                    {
                        NV1IntLibrary arrays__1 = super.insert((N_V1_INT_LIBRARY_.create(countValue)));
                        // assert (s5_.arrays_.accepts(countValue));
                        if ((arrays__1.isArray(countValue)) && count5_ < (3)) {
                            s5_ = super.insert(new Loop4Data(loop4_cache));
                            s5_.arrays_ = s5_.insertAccessor(arrays__1);
                            this.loop4_cache = s5_;
                            this.state_ = state = state | 0b10000 /* add-active doLoop(VirtualFrame, Object, NV1IntLibrary) */;
                        }
                    }
                }
                if (s5_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doLoop(frameValue, countValue, s5_.arrays_);
                }
            }
            {
                NV1IntLibrary loop5_arrays__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            loop5_arrays__ = (N_V1_INT_LIBRARY_.getUncached(countValue));
                            if ((loop5_arrays__.isArray(countValue))) {
                                this.exclude_ = exclude = exclude | 0b10 /* add-excluded doLoop(VirtualFrame, Object, NV1IntLibrary) */;
                                this.loop4_cache = null;
                                state = state & 0xffffffef /* remove-active doLoop(VirtualFrame, Object, NV1IntLibrary) */;
                                this.state_ = state = state | 0b100000 /* add-active doLoop(VirtualFrame, Object, NV1IntLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return doLoop(frameValue, countValue, loop5_arrays__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
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
            Loop3Data s4_ = this.loop3_cache;
            Loop4Data s5_ = this.loop4_cache;
            if ((s1_ == null || s1_.next_ == null) && (s3_ == null || s3_.next_ == null) && (s4_ == null || s4_.next_ == null) && (s5_ == null || s5_.next_ == null)) {
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
    private static final class Loop2Data extends Node {

        @Child Loop2Data next_;
        @Child NV1IntLibrary arrays_;
        @CompilationFinal int cachedCount_;

        Loop2Data(Loop2Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
    @GeneratedBy(NablaLoopNode.class)
    private static final class Loop3Data extends Node {

        @Child Loop3Data next_;
        @CompilationFinal int cachedCount_;

        Loop3Data(Loop3Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

    }
    @GeneratedBy(NablaLoopNode.class)
    private static final class Loop4Data extends Node {

        @Child Loop4Data next_;
        @Child NV1IntLibrary arrays_;

        Loop4Data(Loop4Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
}
