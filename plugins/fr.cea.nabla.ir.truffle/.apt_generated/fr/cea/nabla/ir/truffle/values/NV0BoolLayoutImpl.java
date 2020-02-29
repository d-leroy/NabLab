package fr.cea.nabla.ir.truffle.values;

import java.util.EnumSet;
import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.DynamicObjectFactory;
import com.oracle.truffle.api.object.Layout;
import com.oracle.truffle.api.object.LocationModifier;
import com.oracle.truffle.api.object.ObjectType;
import com.oracle.truffle.api.object.Property;
import com.oracle.truffle.api.object.Shape;
import fr.cea.nabla.ir.truffle.values.NV0BoolLayout;

@GeneratedBy(fr.cea.nabla.ir.truffle.values.NV0BoolLayout.class)
public class NV0BoolLayoutImpl implements NV0BoolLayout {
    
    public static final NV0BoolLayout INSTANCE = new NV0BoolLayoutImpl();
    
    public static class NV0BoolType extends com.oracle.truffle.api.object.ObjectType {
        
    }
    
    protected static final NV0BoolType N_V0_BOOL_TYPE = new NV0BoolType();
    
    protected static final Layout LAYOUT = Layout.newLayout().build();
    protected static final Shape.Allocator N_V0_BOOL_ALLOCATOR = LAYOUT.createAllocator();
    protected static final Property DATA_PROPERTY = Property.create(DATA_IDENTIFIER, N_V0_BOOL_ALLOCATOR.locationForType(boolean.class, EnumSet.of(LocationModifier.NonNull, LocationModifier.Final)), 0);
    
    private static final DynamicObjectFactory N_V0_BOOL_FACTORY = createNV0BoolShape();
    
    protected NV0BoolLayoutImpl() {
    }
    
    private static DynamicObjectFactory createNV0BoolShape() {
        return LAYOUT.createShape(new NV0BoolType())
            .addProperty(DATA_PROPERTY)
            .createFactory();
    }
    
    @Override
    public DynamicObject createNV0Bool(
            boolean data) {
        return createNV0Bool(N_V0_BOOL_FACTORY,
            data);
    }
    
    private static DynamicObject createNV0Bool(
            DynamicObjectFactory factory,
            boolean data) {
        assert factory != null;
        CompilerAsserts.partialEvaluationConstant(factory);
        assert createsNV0Bool(factory);
        assert factory.getShape().hasProperty(DATA_IDENTIFIER);
        return factory.newInstance(
            data);
    }
    
    private static boolean isNV0Bool(DynamicObject object) {
        return isNV0Bool(object.getShape().getObjectType());
    }
    
    private static boolean isNV0Bool(ObjectType objectType) {
        return objectType instanceof NV0BoolType;
    }
    
    private static boolean createsNV0Bool(DynamicObjectFactory factory) {
        return isNV0Bool(factory.getShape().getObjectType());
    }
    
    @Override
    public boolean getData(DynamicObject object) {
        assert isNV0Bool(object);
        assert object.getShape().hasProperty(DATA_IDENTIFIER);
        
        return (boolean) DATA_PROPERTY.get(object, isNV0Bool(object));
    }
    
}
