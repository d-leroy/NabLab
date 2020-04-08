/**
 */
package fr.cea.nabla.ir.ir.impl;

import fr.cea.nabla.ir.ir.Connectivity;
import fr.cea.nabla.ir.ir.IrPackage;
import fr.cea.nabla.ir.ir.ItemType;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connectivity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.cea.nabla.ir.ir.impl.ConnectivityImpl#getName <em>Name</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.impl.ConnectivityImpl#getInTypes <em>In Types</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.impl.ConnectivityImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.impl.ConnectivityImpl#isIndexEqualId <em>Index Equal Id</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.impl.ConnectivityImpl#isMultiple <em>Multiple</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectivityImpl extends IrAnnotableImpl implements Connectivity {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInTypes() <em>In Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<ItemType> inTypes;

	/**
	 * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected ItemType returnType;

	/**
	 * The default value of the '{@link #isIndexEqualId() <em>Index Equal Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIndexEqualId()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INDEX_EQUAL_ID_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIndexEqualId() <em>Index Equal Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIndexEqualId()
	 * @generated
	 * @ordered
	 */
	protected boolean indexEqualId = INDEX_EQUAL_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #isMultiple() <em>Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultiple()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MULTIPLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMultiple() <em>Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultiple()
	 * @generated
	 * @ordered
	 */
	protected boolean multiple = MULTIPLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.CONNECTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.CONNECTIVITY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ItemType> getInTypes() {
		if (inTypes == null) {
			inTypes = new EObjectResolvingEList<ItemType>(ItemType.class, this, IrPackage.CONNECTIVITY__IN_TYPES);
		}
		return inTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ItemType getReturnType() {
		return returnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReturnType(ItemType newReturnType) {
		ItemType oldReturnType = returnType;
		returnType = newReturnType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.CONNECTIVITY__RETURN_TYPE, oldReturnType, returnType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIndexEqualId() {
		return indexEqualId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIndexEqualId(boolean newIndexEqualId) {
		boolean oldIndexEqualId = indexEqualId;
		indexEqualId = newIndexEqualId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.CONNECTIVITY__INDEX_EQUAL_ID, oldIndexEqualId, indexEqualId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isMultiple() {
		return multiple;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMultiple(boolean newMultiple) {
		boolean oldMultiple = multiple;
		multiple = newMultiple;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.CONNECTIVITY__MULTIPLE, oldMultiple, multiple));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.CONNECTIVITY__NAME:
				return getName();
			case IrPackage.CONNECTIVITY__IN_TYPES:
				return getInTypes();
			case IrPackage.CONNECTIVITY__RETURN_TYPE:
				return getReturnType();
			case IrPackage.CONNECTIVITY__INDEX_EQUAL_ID:
				return isIndexEqualId();
			case IrPackage.CONNECTIVITY__MULTIPLE:
				return isMultiple();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IrPackage.CONNECTIVITY__NAME:
				setName((String)newValue);
				return;
			case IrPackage.CONNECTIVITY__IN_TYPES:
				getInTypes().clear();
				getInTypes().addAll((Collection<? extends ItemType>)newValue);
				return;
			case IrPackage.CONNECTIVITY__RETURN_TYPE:
				setReturnType((ItemType)newValue);
				return;
			case IrPackage.CONNECTIVITY__INDEX_EQUAL_ID:
				setIndexEqualId((Boolean)newValue);
				return;
			case IrPackage.CONNECTIVITY__MULTIPLE:
				setMultiple((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case IrPackage.CONNECTIVITY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case IrPackage.CONNECTIVITY__IN_TYPES:
				getInTypes().clear();
				return;
			case IrPackage.CONNECTIVITY__RETURN_TYPE:
				setReturnType((ItemType)null);
				return;
			case IrPackage.CONNECTIVITY__INDEX_EQUAL_ID:
				setIndexEqualId(INDEX_EQUAL_ID_EDEFAULT);
				return;
			case IrPackage.CONNECTIVITY__MULTIPLE:
				setMultiple(MULTIPLE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case IrPackage.CONNECTIVITY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case IrPackage.CONNECTIVITY__IN_TYPES:
				return inTypes != null && !inTypes.isEmpty();
			case IrPackage.CONNECTIVITY__RETURN_TYPE:
				return returnType != null;
			case IrPackage.CONNECTIVITY__INDEX_EQUAL_ID:
				return indexEqualId != INDEX_EQUAL_ID_EDEFAULT;
			case IrPackage.CONNECTIVITY__MULTIPLE:
				return multiple != MULTIPLE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", indexEqualId: ");
		result.append(indexEqualId);
		result.append(", multiple: ");
		result.append(multiple);
		result.append(')');
		return result.toString();
	}

} //ConnectivityImpl
