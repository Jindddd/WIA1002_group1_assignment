package com.assignment;

import java.util.Iterator;

public class CharacterNodeIter<T> implements Iterator<CharacterNode<T>> {

	enum ProcessStages {
		ProcessParent, ProcessChildCurNode, ProcessChildSubNode
	}

	private CharacterNode<T> characterNode;
	private ProcessStages doNext;
	private CharacterNode<T> next;
	private Iterator<CharacterNode<T>> childrenCurNodeIter;
	private Iterator<CharacterNode<T>> childrenSubNodeIter;

	public CharacterNodeIter(CharacterNode<T> characterNode) {
		this.characterNode = characterNode;
		// Start with the parent node
		this.doNext = ProcessStages.ProcessParent;
		this.childrenCurNodeIter = characterNode.children.iterator();
	}

	@Override
	public boolean hasNext() {
		if (this.doNext == ProcessStages.ProcessParent) {
			// Point next to the current parent node
			this.next = this.characterNode;
			// Set do next to process children current node
			this.doNext = ProcessStages.ProcessChildCurNode;
			return true;
		}

		if (this.doNext == ProcessStages.ProcessChildCurNode) {
			// Check whether there are children to process
			if (childrenCurNodeIter.hasNext()) {
				// Retrieve the next child
				CharacterNode<T> childDirect = childrenCurNodeIter.next();
				// Initialise the children sub node iterator to allow iterating over sub nodes of the current child
				childrenSubNodeIter = childDirect.iterator();
				this.doNext = ProcessStages.ProcessChildSubNode;
				// Recursively call hasNext() method to check for next available node
				return hasNext();
			}

			else {
				this.doNext = null;
				return false;
			}
		}
		
		if (this.doNext == ProcessStages.ProcessChildSubNode) {
			if (childrenSubNodeIter.hasNext()) {
				// Set next to next sub node
				this.next = childrenSubNodeIter.next();
				return true;
			}
			else {
				this.next = null;
				this.doNext = ProcessStages.ProcessChildCurNode;
				// Recursively call hasNext() method to check for next available sub node or children
				return hasNext();
			}
		}
		return false;
	}

	@Override
	public CharacterNode<T> next() {
		return this.next;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
