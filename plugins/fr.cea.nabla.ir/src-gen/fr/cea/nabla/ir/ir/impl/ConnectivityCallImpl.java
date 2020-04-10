/**
 */
package fr.cea.nabla.ir.ir.impl;

import fr.cea.nabla.ir.ir.Connectivity;
import fr.cea.nabla.ir.ir.ConnectivityCall;
import fr.cea.nabla.ir.ir.IrPackage;
import fr.cea.nabla.ir.ir.ItemId;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connectivity Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.cea.nabla.ir.ir.impl.ConnectivityCallImpl#getConnectivity <em>Connectivity</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.impl.ConnectivityCallImpl#getArgs <em>Args</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectivityCallImpl extends ContainerImpl implements ConnectivityCall {
	/**
	 * The cached value of the '{@link #getConnectivity() <em>Connectivity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectivity()
	 * @generated
	 * @ordered
	 */
	protected Connectivity connectivity;

	/**
	 * The cached value of the '{@link #getArgs() <em>Args</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArgs()
	 * @generated
	 * @ordered
	 */
	protected EList<ItemId> args;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectivityCallImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.CONNECTIVITY_CALL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Connectivity getConnectivity() {
		if (connectivity != null && connectivity.eIsProxy()) {
			InternalEObject oldConnectivity = (InternalEObject)connectivity;
			connectivity = (Connectivity)eResolveProxy(oldConnectivity);
			if (connectivity != oldConnectivity) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.CONNECTIVITY_CALL__CONNECTIVITY, oldConnectivity, connectivity));
			}
		}
		return connectivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connectivity basicGetConnectivity() {
		return connectivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConnectivity(Connectivity newConnectivity) {
		Connectivity oldConnectivity = connectivity;
		connectivity = newConnectivity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.CONNECTIVITY_CALL__CONNECTIVITY, oldConnectivity, connectivity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ItemId> getArgs() {
		if (args == null) {
			args = new EObjectEList<ItemId>(ItemId.class, this, IrPackage.CONNECTIVITY_CALL__ARGS);
		}
		return args;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.CONNECTIVITY_CALL__CONNECTIVITY:
				if (resolve) return getConnectivity();
				return basicGetConnectivity();
			case IrPackage.CONNECTIVITY_CALL__ARGS:
				return getArgs();
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
			case IrPackage.CONNECTIVITY_CALL__CONNECTIVITY:
				setConnectivity((Connectivity)newValue);
				return;
			case IrPackage.CONNECTIVITY_CALL__ARGS:
				getArgs().clear();
				getArgs().addAll((Collection<? extends ItemId>)newValue);
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
			case IrPackage.CONNECTIVITY_CALL__CONNECTIVITY:
				setConnectivity((Connectivity)null);
				return;
			case IrPackage.CONNECTIVITY_CALL__ARGS:
				getArgs().clear();
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
			case IrPackage.CONNECTIVITY_CALL__CONNECTIVITY:
				return connectivity != null;
			case IrPackage.CONNECTIVITY_CALL__ARGS:
				return args != null && !args.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ConnectivityCallImpl
