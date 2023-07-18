import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private int remainSize;
    private int[][] data;
    private int head, tail;
    private Map<Integer, Integer> indexMap;

    public LRUCache(int capacity) {
        data = new int[3 * capacity][2];
        indexMap = new HashMap<>((int) (capacity / 0.75f) + 1);
        remainSize = capacity;
        head = 0;
        tail = 0;
    }

    public int get(int key) {
        fixData();
        Integer index = indexMap.replace(key, tail);
        if (index == null) return -1;
        // move index to tail
        data[tail][0] = data[index][0];
        data[tail][1] = data[index][1];
        setEmpty(index);
        return data[tail++][1];
    }

    public void put(int key, int value) {
        fixData();
        Integer index = indexMap.replace(key, tail);
        if (index != null) {
            // move index to tail
            setEmpty(index);
            data[tail][0] = key;
            data[tail][1] = value;
            tail++;
        } else {
            // new key
            if (remainSize == 0) {
                // evict
                while (isEmpty(head)) {
                    head++;
                }
                // update head
                indexMap.remove(data[head][0]);
                head++;
            } else {
                remainSize--;
            }
            indexMap.put(key, tail);
            data[tail][0] = key;
            data[tail++][1] = value;
        }
    }

    private void fixData() {
        if (tail != data.length) {
            return;
        }
        int i = head, j = 0;
        for (; i < data.length; i++) {
            if (!isEmpty(i)) {
                // move from i to j
                data[j][0] = data[i][0];
                data[j][1] = data[i][1];
                indexMap.replace(data[i][0], j++);
            }
        }
        tail = j;
        head = 0;
    }


    private boolean isEmpty(int index) {
        return data[index][0] == -1;
    }

    private void setEmpty(int index) {
        data[index][1] = data[index][0] = -1;
    }

    private void printData() {
        System.out.printf("array:%s\nhead:%d tail:%d\n", Arrays.deepToString(data), head, tail);
    }
}