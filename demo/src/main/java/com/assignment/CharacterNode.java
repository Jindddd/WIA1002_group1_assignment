package com.assignment;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// Using tree data structure
public class CharacterNode<T> implements Iterable<CharacterNode<T>> {

	public T name;
	public String armyType;
	public int leadership;
	public int strength;
	public int intelligence;
	public int politic;
	public int hitPoint;
	public String position;
	public String department;
	public CharacterNode<T> parent;
	public List<CharacterNode<T>> children;

	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	private List<CharacterNode<T>> elementsIndex;

	// Constructor for Emperor and Chief
	public CharacterNode(T name, String position, String department, String armyType, int leadership, int strength, int intelligence,
						 int politic, int hitPoint) {
		this.name = name;
		this.armyType = armyType;
		this.position = position;
		this.leadership = leadership;
		this.strength = strength;
		this.intelligence = intelligence;
		this.politic = politic;
		this.hitPoint = hitPoint;
		this.department = department;
		this.children = new LinkedList<>();
		this.elementsIndex = new LinkedList<>();
		this.elementsIndex.add(this);
	}

	// Constructor for general node
	public CharacterNode(T name, String position, String armyType, int leadership, int strength, int intelligence, int politic,
						 int hitPoint) {
		this.name = name;
		this.armyType = armyType;
		this.position = position;
		this.leadership = leadership;
		this.strength = strength;
		this.intelligence = intelligence;
		this.politic = politic;
		this.hitPoint = hitPoint;
		// Assign general to management or military based on intelligence and strength
		if (position.equals("General")) {
			this.department = intelligence > strength ? "Management" : "Military";
		}
		this.children = new LinkedList<>();
		this.elementsIndex = new LinkedList<>();
		this.elementsIndex.add(this);
	}

	// For general
	public CharacterNode<T> addChild(T childName, String position, String armyType, int leadership, int strength, int intelligence,
									 int politic, int hitPoint) {
		CharacterNode<T> childNode = new CharacterNode<T>(childName, position, armyType, leadership, strength, intelligence, politic, hitPoint);
		childNode.parent = this;
		this.children.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}

	// For chief
	public CharacterNode<T> addChild(T childName, String position, String department, String armyType, int leadership, int strength,
									 int intelligence, int politic, int hitPoint) {
		CharacterNode<T> childNode = new CharacterNode<T>(childName, position, department, armyType, leadership, strength, intelligence, politic, hitPoint);
		childNode.parent = this;
		this.children.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}

	public int getLevel() {
		if (this.isRoot())
			return 0;
		else
			return parent.getLevel() + 1;
	}

	private void registerChildForSearch(CharacterNode<T> node) {
		elementsIndex.add(node);
		if (parent != null)
			parent.registerChildForSearch(node);
	}

	public CharacterNode<T> findTreeNode(Comparable<T> cmp) {
		for (CharacterNode<T> element : this.elementsIndex) {
			T elementData = element.name;
			// By using the custom comparator if the result is equal 0, return the element
			if (cmp.compareTo(elementData) == 0)
				return element;
		}

		return null;
	}

	@Override
	public String toString() {
		if (position.equals("Emperor")) {
			return name + " (" + position + ")";
		} else if (position.equals("Chief")) {
			return name + " (" + position + " of " + department + ")";
		}
		return name + " (" + position + " of " + department + ")";
	}

	@Override
	public Iterator<CharacterNode<T>> iterator() {
		CharacterNodeIter<T> iter = new CharacterNodeIter<T>(this);
		return iter;
	}

}
