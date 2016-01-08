public class SimpleLinkPrac{
	
	
	public void addNodes(int data){
		DNode dNode = new DNode(data);

		if (root == null){
			root = dNode; 
			root.previousNode = null; 
			root.nextNode = null;
		}
		else{
			current = root; 
			while (current.nextNode!= null){
				current = current.nextNode; 
			}
			current.nextNode = dNode; 
			dNode.previousNode = current; 
			dNode.nextNode = null;
		}
	}

	public void insertNode(int data, int after){
		DNode dNode = new DNode(data);
		int ithNode = 1; 
		current I = root; 
		while (ithNode != after){
			current = current.nextNode
			ithNode++
		}

		temp = current.nextNode; 
		current.nextNode = dNode; 
		dNode.nextNode = temp; 
		temp.previousNode = dNode;
		dNode.previousNode = current; 

	}
	public void deleteNode(int nodeNumber){
		int ithNode = 1; 
		current = root; 
		if(nodeNumber==1){
			root = current/nextNode;

		}
		else{

		}

	}

	public static void main(String[] args){

	}
}