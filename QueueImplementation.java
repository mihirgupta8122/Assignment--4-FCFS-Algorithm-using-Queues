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
        if (this.isEmpty()) {
            return null;
        }
        T toReturnData = dataQueue.getData();
        this.dataQueue = dataQueue.getNext();
        return toReturnData;
    }

    @Override
    public T getFront() {
        if (this.isEmpty()) {
            return null;
        }
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
                String result = stringsTest.dequeue();
                if (result == null) {
                    System.out.println("Queue is already empty");
                } else {
                    System.out.println("Dequeued: " + result);
                }
            } else if (input.equalsIgnoreCase("front")) {
                String result = stringsTest.getFront();
                if (result == null) {
                    System.out.println("Queue is already empty");
                } else {
                    System.out.println("Front: " + result);
                }
            } else if (input.equalsIgnoreCase("empty")) {
                System.out.println("Empty: " + stringsTest.isEmpty());
            } else {
                stringsTest.enqueue(input);
            }
        }
        getInput.close();
    }
}