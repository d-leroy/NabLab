// CheckStyle: start generated
package fr.cea.nabla.interpreter.values;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.TruffleLanguage.ContextReference;
import com.oracle.truffle.api.TruffleLanguage.Env;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.NablaLanguage;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.runtime.NablaContext;
import fr.cea.nabla.interpreter.values.CreateNablaValueNode;
import fr.cea.nabla.interpreter.values.NablaValue;
import java.util.concurrent.locks.Lock;
import org.graalvm.polyglot.Value;

@GeneratedBy(CreateNablaValueNode.class)
public final class CreateNablaValueNodeGen extends CreateNablaValueNode {

    private static final LibraryFactory<InteropLibrary> INTEROP_LIBRARY_ = LibraryFactory.resolve(InteropLibrary.class);

    @Child private NablaExpressionNode expression_;
    @CompilationFinal private volatile int state_;
    @CompilationFinal private ContextReference<NablaContext> nablaLanguageContextReference_;
    @CompilationFinal private HostObjectData hostObject_cache;
    @Child private InteropLibrary createNablaValueInt11_pointers_;
    @Child private InteropLibrary createNablaValueDouble11_pointers_;

    private CreateNablaValueNodeGen(Class<?> expectedType, NablaExpressionNode expression) {
        super(expectedType);
        this.expression_ = expression;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object expressionValue_ = this.expression_.executeGeneric(frameValue);
        if ((state & 0b1) != 0 /* is-active createNablaValue(NablaValue) */ && expressionValue_ instanceof NablaValue) {
            NablaValue expressionValue__ = (NablaValue) expressionValue_;
            return createNablaValue(expressionValue__);
        }
        if ((state & 0b10) != 0 /* is-active doHostObjectValue(Value) */ && expressionValue_ instanceof Value) {
            Value expressionValue__ = (Value) expressionValue_;
            if ((expressionValue__.isHostObject())) {
                return doHostObjectValue(expressionValue__);
            }
        }
        if ((state & 0b100) != 0 /* is-active doHostObject(Object, NablaContext, Env) */) {
            HostObjectData s3_ = this.hostObject_cache;
            while (s3_ != null) {
                {
                    ContextReference<NablaContext> nablaLanguageContextReference__ = this.nablaLanguageContextReference_;
                    NablaContext context__ = nablaLanguageContextReference__.get();
                    if ((s3_.env_.isHostObject(expressionValue_))) {
                        return doHostObject(expressionValue_, context__, s3_.env_);
                    }
                }
                s3_ = s3_.next_;
            }
        }
        if ((state & 0b1000) != 0 /* is-active createNablaValue(Boolean) */ && expressionValue_ instanceof Boolean) {
            Boolean expressionValue__ = (Boolean) expressionValue_;
            return createNablaValue(expressionValue__);
        }
        if ((state & 0b10000) != 0 /* is-active createNablaValue(boolean[]) */ && expressionValue_ instanceof boolean[]) {
            boolean[] expressionValue__ = (boolean[]) expressionValue_;
            return createNablaValue(expressionValue__);
        }
        if ((state & 0b100000) != 0 /* is-active createNablaValue(boolean[][]) */ && expressionValue_ instanceof boolean[][]) {
            boolean[][] expressionValue__ = (boolean[][]) expressionValue_;
            return createNablaValue(expressionValue__);
        }
        if ((state & 0b1000000) != 0 /* is-active createNablaValue(Integer) */ && expressionValue_ instanceof Integer) {
            Integer expressionValue__ = (Integer) expressionValue_;
            return createNablaValue(expressionValue__);
        }
        if ((state & 0b10000000) != 0 /* is-active createNablaValue(Long) */ && expressionValue_ instanceof Long) {
            Long expressionValue__ = (Long) expressionValue_;
            return createNablaValue(expressionValue__);
        }
        if ((state & 0b100000000) != 0 /* is-active createNablaValue(int[]) */ && expressionValue_ instanceof int[]) {
            int[] expressionValue__ = (int[]) expressionValue_;
            return createNablaValue(expressionValue__);
        }
        if ((state & 0b1000000000) != 0 /* is-active createNablaValueInt1(Value) */ && expressionValue_ instanceof Value) {
            Value expressionValue__ = (Value) expressionValue_;
            assert (expectsInt1());
            if ((expressionValue__.isNativePointer()) && (expressionValue__.hasArrayElements())) {
                return createNablaValueInt1(expressionValue__);
            }
        }
        if ((state & 0b10000000000) != 0 /* is-active createNablaValueInt1(Object, InteropLibrary) */) {
            assert (expectsInt1());
            if ((this.createNablaValueInt11_pointers_.isPointer(expressionValue_)) && (this.createNablaValueInt11_pointers_.hasArrayElements(expressionValue_))) {
                return createNablaValueInt1(expressionValue_, this.createNablaValueInt11_pointers_);
            }
        }
        if ((state & 0b100000000000) != 0 /* is-active createNablaValue(int[][]) */ && expressionValue_ instanceof int[][]) {
            int[][] expressionValue__ = (int[][]) expressionValue_;
            return createNablaValue(expressionValue__);
        }
        if ((state & 0b1000000000000) != 0 /* is-active createNablaValue(Double) */ && expressionValue_ instanceof Double) {
            Double expressionValue__ = (Double) expressionValue_;
            return createNablaValue(expressionValue__);
        }
        if ((state & 0b10000000000000) != 0 /* is-active createNablaValue(double[]) */ && expressionValue_ instanceof double[]) {
            double[] expressionValue__ = (double[]) expressionValue_;
            return createNablaValue(expressionValue__);
        }
        if ((state & 0b100000000000000) != 0 /* is-active createNablaValueDouble1(Value) */ && expressionValue_ instanceof Value) {
            Value expressionValue__ = (Value) expressionValue_;
            assert (expectsDouble1());
            if ((expressionValue__.isNativePointer()) && (expressionValue__.hasArrayElements())) {
                return createNablaValueDouble1(expressionValue__);
            }
        }
        if ((state & 0b1000000000000000) != 0 /* is-active createNablaValueDouble1(Object, InteropLibrary) */) {
            assert (expectsDouble1());
            if ((this.createNablaValueDouble11_pointers_.isPointer(expressionValue_)) && (this.createNablaValueDouble11_pointers_.hasArrayElements(expressionValue_))) {
                return createNablaValueDouble1(expressionValue_, this.createNablaValueDouble11_pointers_);
            }
        }
        if ((state & 0x10000) != 0 /* is-active createNablaValue(double[][]) */ && expressionValue_ instanceof double[][]) {
            double[][] expressionValue__ = (double[][]) expressionValue_;
            return createNablaValue(expressionValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(expressionValue_);
    }

    private Object executeAndSpecialize(Object expressionValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        try {
            if (expressionValue instanceof NablaValue) {
                NablaValue expressionValue_ = (NablaValue) expressionValue;
                this.state_ = state = state | 0b1 /* add-active createNablaValue(NablaValue) */;
                lock.unlock();
                hasLock = false;
                return createNablaValue(expressionValue_);
            }
            if (expressionValue instanceof Value) {
                Value expressionValue_ = (Value) expressionValue;
                if ((expressionValue_.isHostObject())) {
                    this.state_ = state = state | 0b10 /* add-active doHostObjectValue(Value) */;
                    lock.unlock();
                    hasLock = false;
                    return doHostObjectValue(expressionValue_);
                }
            }
            {
                NablaContext context__ = null;
                int count3_ = 0;
                HostObjectData s3_ = this.hostObject_cache;
                if ((state & 0b100) != 0 /* is-active doHostObject(Object, NablaContext, Env) */) {
                    while (s3_ != null) {
                        {
                            ContextReference<NablaContext> nablaLanguageContextReference__1 = this.nablaLanguageContextReference_;
                            if (nablaLanguageContextReference__1 == null) {
                                this.nablaLanguageContextReference_ = nablaLanguageContextReference__1 = super.lookupContextReference(NablaLanguage.class);
                            }
                            context__ = nablaLanguageContextReference__1.get();
                            if ((s3_.env_.isHostObject(expressionValue))) {
                                break;
                            }
                        }
                        s3_ = s3_.next_;
                        count3_++;
                    }
                }
                if (s3_ == null) {
                    {
                        ContextReference<NablaContext> nablaLanguageContextReference__2 = this.nablaLanguageContextReference_;
                        if (nablaLanguageContextReference__2 == null) {
                            this.nablaLanguageContextReference_ = nablaLanguageContextReference__2 = super.lookupContextReference(NablaLanguage.class);
                        }
                        context__ = nablaLanguageContextReference__2.get();
                        Env env__ = (context__.getEnv());
                        if ((env__.isHostObject(expressionValue)) && count3_ < (3)) {
                            s3_ = new HostObjectData(hostObject_cache);
                            s3_.env_ = env__;
                            this.hostObject_cache = s3_;
                            this.state_ = state = state | 0b100 /* add-active doHostObject(Object, NablaContext, Env) */;
                        }
                    }
                }
                if (s3_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doHostObject(expressionValue, context__, s3_.env_);
                }
            }
            if (expressionValue instanceof Boolean) {
                Boolean expressionValue_ = (Boolean) expressionValue;
                this.state_ = state = state | 0b1000 /* add-active createNablaValue(Boolean) */;
                lock.unlock();
                hasLock = false;
                return createNablaValue(expressionValue_);
            }
            if (expressionValue instanceof boolean[]) {
                boolean[] expressionValue_ = (boolean[]) expressionValue;
                this.state_ = state = state | 0b10000 /* add-active createNablaValue(boolean[]) */;
                lock.unlock();
                hasLock = false;
                return createNablaValue(expressionValue_);
            }
            if (expressionValue instanceof boolean[][]) {
                boolean[][] expressionValue_ = (boolean[][]) expressionValue;
                this.state_ = state = state | 0b100000 /* add-active createNablaValue(boolean[][]) */;
                lock.unlock();
                hasLock = false;
                return createNablaValue(expressionValue_);
            }
            if (expressionValue instanceof Integer) {
                Integer expressionValue_ = (Integer) expressionValue;
                this.state_ = state = state | 0b1000000 /* add-active createNablaValue(Integer) */;
                lock.unlock();
                hasLock = false;
                return createNablaValue(expressionValue_);
            }
            if (expressionValue instanceof Long) {
                Long expressionValue_ = (Long) expressionValue;
                this.state_ = state = state | 0b10000000 /* add-active createNablaValue(Long) */;
                lock.unlock();
                hasLock = false;
                return createNablaValue(expressionValue_);
            }
            if (expressionValue instanceof int[]) {
                int[] expressionValue_ = (int[]) expressionValue;
                this.state_ = state = state | 0b100000000 /* add-active createNablaValue(int[]) */;
                lock.unlock();
                hasLock = false;
                return createNablaValue(expressionValue_);
            }
            if (expressionValue instanceof Value) {
                Value expressionValue_ = (Value) expressionValue;
                if ((expectsInt1()) && (expressionValue_.isNativePointer()) && (expressionValue_.hasArrayElements())) {
                    this.state_ = state = state | 0b1000000000 /* add-active createNablaValueInt1(Value) */;
                    lock.unlock();
                    hasLock = false;
                    return createNablaValueInt1(expressionValue_);
                }
            }
            if ((expectsInt1())) {
                InteropLibrary createNablaValueInt11_pointers__ = super.insert((INTEROP_LIBRARY_.createDispatched(3)));
                if ((createNablaValueInt11_pointers__.isPointer(expressionValue)) && (createNablaValueInt11_pointers__.hasArrayElements(expressionValue))) {
                    this.createNablaValueInt11_pointers_ = super.insert(createNablaValueInt11_pointers__);
                    this.state_ = state = state | 0b10000000000 /* add-active createNablaValueInt1(Object, InteropLibrary) */;
                    lock.unlock();
                    hasLock = false;
                    return createNablaValueInt1(expressionValue, createNablaValueInt11_pointers__);
                }
            }
            if (expressionValue instanceof int[][]) {
                int[][] expressionValue_ = (int[][]) expressionValue;
                this.state_ = state = state | 0b100000000000 /* add-active createNablaValue(int[][]) */;
                lock.unlock();
                hasLock = false;
                return createNablaValue(expressionValue_);
            }
            if (expressionValue instanceof Double) {
                Double expressionValue_ = (Double) expressionValue;
                this.state_ = state = state | 0b1000000000000 /* add-active createNablaValue(Double) */;
                lock.unlock();
                hasLock = false;
                return createNablaValue(expressionValue_);
            }
            if (expressionValue instanceof double[]) {
                double[] expressionValue_ = (double[]) expressionValue;
                this.state_ = state = state | 0b10000000000000 /* add-active createNablaValue(double[]) */;
                lock.unlock();
                hasLock = false;
                return createNablaValue(expressionValue_);
            }
            if (expressionValue instanceof Value) {
                Value expressionValue_ = (Value) expressionValue;
                if ((expectsDouble1()) && (expressionValue_.isNativePointer()) && (expressionValue_.hasArrayElements())) {
                    this.state_ = state = state | 0b100000000000000 /* add-active createNablaValueDouble1(Value) */;
                    lock.unlock();
                    hasLock = false;
                    return createNablaValueDouble1(expressionValue_);
                }
            }
            if ((expectsDouble1())) {
                InteropLibrary createNablaValueDouble11_pointers__ = super.insert((INTEROP_LIBRARY_.createDispatched(3)));
                if ((createNablaValueDouble11_pointers__.isPointer(expressionValue)) && (createNablaValueDouble11_pointers__.hasArrayElements(expressionValue))) {
                    this.createNablaValueDouble11_pointers_ = super.insert(createNablaValueDouble11_pointers__);
                    this.state_ = state = state | 0b1000000000000000 /* add-active createNablaValueDouble1(Object, InteropLibrary) */;
                    lock.unlock();
                    hasLock = false;
                    return createNablaValueDouble1(expressionValue, createNablaValueDouble11_pointers__);
                }
            }
            if (expressionValue instanceof double[][]) {
                double[][] expressionValue_ = (double[][]) expressionValue;
                this.state_ = state = state | 0x10000 /* add-active createNablaValue(double[][]) */;
                lock.unlock();
                hasLock = false;
                return createNablaValue(expressionValue_);
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.expression_}, expressionValue);
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
            HostObjectData s3_ = this.hostObject_cache;
            if ((s3_ == null || s3_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static CreateNablaValueNode create(Class<?> expectedType, NablaExpressionNode expression) {
        return new CreateNablaValueNodeGen(expectedType, expression);
    }

    @GeneratedBy(CreateNablaValueNode.class)
    private static final class HostObjectData {

        @CompilationFinal HostObjectData next_;
        @CompilationFinal Env env_;

        HostObjectData(HostObjectData next_) {
            this.next_ = next_;
        }

    }
}
