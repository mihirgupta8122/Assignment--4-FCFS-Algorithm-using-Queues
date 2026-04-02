import java.util.Scanner;

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

    public static void main(String[] args) {
        QueueImplementation<String> stringsTest = new QueueImplementation<>();
        Scanner getInput = new Scanner(System.in);
        while (true) {
            String input = getInput.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            } else if (input.equalsIgnoreCase("break")) {
                break;
            } else if (input.equalsIgnoreCase("dequeue")) {
                System.out.println("Dequeued: " + stringsTest.dequeue());
            } else if (input.equalsIgnoreCase("front")) {
                System.out.println("Front: " + stringsTest.getFront());
            } else {
                stringsTest.enqueue(input);
            }
        }
        getInput.close();
    }
}