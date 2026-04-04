package fcfs;
public class LinkedListObj<T> {
    LinkedListObj<T> next = null;
    T data = null;

    public LinkedListObj() {
        // To create without data
    }

    public LinkedListObj(T initialData) {
        this.data = initialData;
    }

    public void addData(T newData) {
        if (this.data == null) {
            this.data = newData;
        } else {
            // Traverse to the end of the list to add the new node
            LinkedListObj<T> current = this;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new LinkedListObj<T>(newData);
        }
    }

    public T getData() {
        return this.data;
    }

    public LinkedListObj<T> getNext() {
        return this.next;
    }
}
