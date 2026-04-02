public class QueueImplementation<T> implements QueueInterface<T> {
    LinkedListObj<T> dataQueue = new LinkedListObj<T>();

    @Override
    public void enqueue(T newEntry) {
        if (dataQueue == null) {
            dataQueue = new LinkedListObj<T>(newEntry);
        } else {
            dataQueue.addData(newEntry);
        }
    }

    @Override
    public T dequeue() {
        T toReturnData = dataQueue.getData();
        this.dataQueue = dataQueue.getNext();
        return toReturnData;
    }

    @Override
    public T getFront() {
        return dataQueue.getData();
    }

    @Override
    public boolean isEmpty() {
        if (this.dataQueue == null) {
            return true;
        }
        return (this.dataQueue.getData() == null);
    }

    @Override
    public void clear() {
        this.dataQueue = new LinkedListObj<T>();
    }
}