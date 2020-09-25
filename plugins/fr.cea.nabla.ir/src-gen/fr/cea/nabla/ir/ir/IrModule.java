/**
 */
package fr.cea.nabla.ir.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.cea.nabla.ir.ir.IrModule#getName <em>Name</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.IrModule#getImports <em>Imports</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.IrModule#getItemTypes <em>Item Types</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.IrModule#getFunctions <em>Functions</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.IrModule#getConnectivities <em>Connectivities</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.IrModule#getOptions <em>Options</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.IrModule#getVariables <em>Variables</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.IrModule#getMeshClassName <em>Mesh Class Name</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.IrModule#getInitNodeCoordVariable <em>Init Node Coord Variable</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.IrModule#getNodeCoordVariable <em>Node Coord Variable</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.IrModule#getTimeVariable <em>Time Variable</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.IrModule#getDeltatVariable <em>Deltat Variable</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.IrModule#getJobs <em>Jobs</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.IrModule#getPostProcessingInfo <em>Post Processing Info</em>}</li>
 * </ul>
 *
 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule()
 * @model
 * @generated
 */
public interface IrModule extends JobContainer, TimeLoopContainer {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule_Name()
	 * @model unique="false" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link fr.cea.nabla.ir.ir.IrModule#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
	 * The list contents are of type {@link fr.cea.nabla.ir.ir.Import}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' containment reference list.
	 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule_Imports()
	 * @model containment="true"
	 * @generated
	 */
	EList<Import> getImports();

	/**
	 * Returns the value of the '<em><b>Item Types</b></em>' containment reference list.
	 * The list contents are of type {@link fr.cea.nabla.ir.ir.ItemType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Item Types</em>' containment reference list.
	 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule_ItemTypes()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ItemType> getItemTypes();

	/**
	 * Returns the value of the '<em><b>Functions</b></em>' containment reference list.
	 * The list contents are of type {@link fr.cea.nabla.ir.ir.Function}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Functions</em>' containment reference list.
	 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule_Functions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Function> getFunctions();

	/**
	 * Returns the value of the '<em><b>Connectivities</b></em>' containment reference list.
	 * The list contents are of type {@link fr.cea.nabla.ir.ir.Connectivity}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connectivities</em>' containment reference list.
	 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule_Connectivities()
	 * @model containment="true"
	 * @generated
	 */
	EList<Connectivity> getConnectivities();

	/**
	 * Returns the value of the '<em><b>Options</b></em>' containment reference list.
	 * The list contents are of type {@link fr.cea.nabla.ir.ir.SimpleVariable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' containment reference list.
	 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule_Options()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<SimpleVariable> getOptions();

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
	 * The list contents are of type {@link fr.cea.nabla.ir.ir.Variable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference list.
	 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule_Variables()
	 * @model containment="true"
	 * @generated
	 */
	EList<Variable> getVariables();

	/**
	 * Returns the value of the '<em><b>Mesh Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mesh Class Name</em>' attribute.
	 * @see #setMeshClassName(String)
	 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule_MeshClassName()
	 * @model required="true"
	 * @generated
	 */
	String getMeshClassName();

	/**
	 * Sets the value of the '{@link fr.cea.nabla.ir.ir.IrModule#getMeshClassName <em>Mesh Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mesh Class Name</em>' attribute.
	 * @see #getMeshClassName()
	 * @generated
	 */
	void setMeshClassName(String value);

	/**
	 * Returns the value of the '<em><b>Init Node Coord Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Node Coord Variable</em>' reference.
	 * @see #setInitNodeCoordVariable(ConnectivityVariable)
	 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule_InitNodeCoordVariable()
	 * @model required="true"
	 * @generated
	 */
	ConnectivityVariable getInitNodeCoordVariable();

	/**
	 * Sets the value of the '{@link fr.cea.nabla.ir.ir.IrModule#getInitNodeCoordVariable <em>Init Node Coord Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Init Node Coord Variable</em>' reference.
	 * @see #getInitNodeCoordVariable()
	 * @generated
	 */
	void setInitNodeCoordVariable(ConnectivityVariable value);

	/**
	 * Returns the value of the '<em><b>Node Coord Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Coord Variable</em>' reference.
	 * @see #setNodeCoordVariable(ConnectivityVariable)
	 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule_NodeCoordVariable()
	 * @model required="true"
	 * @generated
	 */
	ConnectivityVariable getNodeCoordVariable();

	/**
	 * Sets the value of the '{@link fr.cea.nabla.ir.ir.IrModule#getNodeCoordVariable <em>Node Coord Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Coord Variable</em>' reference.
	 * @see #getNodeCoordVariable()
	 * @generated
	 */
	void setNodeCoordVariable(ConnectivityVariable value);

	/**
	 * Returns the value of the '<em><b>Time Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Variable</em>' reference.
	 * @see #setTimeVariable(SimpleVariable)
	 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule_TimeVariable()
	 * @model required="true"
	 * @generated
	 */
	SimpleVariable getTimeVariable();

	/**
	 * Sets the value of the '{@link fr.cea.nabla.ir.ir.IrModule#getTimeVariable <em>Time Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Variable</em>' reference.
	 * @see #getTimeVariable()
	 * @generated
	 */
	void setTimeVariable(SimpleVariable value);

	/**
	 * Returns the value of the '<em><b>Deltat Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deltat Variable</em>' reference.
	 * @see #setDeltatVariable(SimpleVariable)
	 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule_DeltatVariable()
	 * @model required="true"
	 * @generated
	 */
	SimpleVariable getDeltatVariable();

	/**
	 * Sets the value of the '{@link fr.cea.nabla.ir.ir.IrModule#getDeltatVariable <em>Deltat Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deltat Variable</em>' reference.
	 * @see #getDeltatVariable()
	 * @generated
	 */
	void setDeltatVariable(SimpleVariable value);

	/**
	 * Returns the value of the '<em><b>Jobs</b></em>' containment reference list.
	 * The list contents are of type {@link fr.cea.nabla.ir.ir.Job}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jobs</em>' containment reference list.
	 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule_Jobs()
	 * @model containment="true"
	 * @generated
	 */
	EList<Job> getJobs();

	/**
	 * Returns the value of the '<em><b>Post Processing Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Post Processing Info</em>' containment reference.
	 * @see #setPostProcessingInfo(PostProcessingInfo)
	 * @see fr.cea.nabla.ir.ir.IrPackage#getIrModule_PostProcessingInfo()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	PostProcessingInfo getPostProcessingInfo();

	/**
	 * Sets the value of the '{@link fr.cea.nabla.ir.ir.IrModule#getPostProcessingInfo <em>Post Processing Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Post Processing Info</em>' containment reference.
	 * @see #getPostProcessingInfo()
	 * @generated
	 */
	void setPostProcessingInfo(PostProcessingInfo value);

} // IrModule
