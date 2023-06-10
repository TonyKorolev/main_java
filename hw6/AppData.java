import java.util.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class AppData {
    private Map<String, List<Integer>> data = new HashMap<>();

    public void addColumn(String title, List<Integer> numbers) {
        data.put(title, numbers);
    }

    public void display() {
        System.out.println(data);
    }

    public void save(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            List<String> keyList = new ArrayList<String>(data.keySet());

            // записываем строку с названием колонок
            int columnsCount = keyList.size();
            for (int i = 0; i < columnsCount; i++) {
                String key = keyList.get(i);
                writer.write(key);
                if (i == columnsCount - 1) {
                    writer.write('\n');
                } else {
                    writer.write(';');
                }
            }

            // количество строк берем по первой колонке
            int rowsCount = data.get(keyList.get(0)).size();
            for (int j = 0; j < rowsCount; j++) {
                // записываем строку с значениями
                for (int i = 0; i < columnsCount; i++) {
                    String key = keyList.get(i);
                    List<Integer> column = data.get(key);
                    Integer value = column.get(j);

                    writer.write(value.toString());
                    if (i == columnsCount - 1) {
                        writer.write('\n');
                    } else {
                        writer.write(';');
                    }
                }
            }

            writer.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    public void read(String fileName) {
        try {
            BufferedReader buffer = new BufferedReader(
                 new FileReader(fileName));

            // читаем строку с заголовками и разбиваем ее на ;
            String header = buffer.readLine();
            List<String> keyList = new ArrayList<String>(Arrays.asList(header.split(";")));

            // добавляем ключи из заголовка с пустыми списками
            int columnsCount = keyList.size();
            for (int i = 0; i < columnsCount; i++) {
                String key = keyList.get(i);
                List<Integer> l = new ArrayList<>();
                addColumn(key, l);
            }

            String line;
            while ((line = buffer.readLine()) != null) {
                // читаем построчно и разбиваем строку на ;
                List<String> values = new ArrayList<String>(Arrays.asList(line.split(";")));
                for (int i = 0; i < columnsCount; i++) {
                    String key = keyList.get(i);
                    data.get(key).add(Integer.parseInt(values.get(i)));
                }
            }

            buffer.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}
