package ru.anton.snakeAI.neuralModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor slonikmak on 06.12.2017.
 */
public class TrainingManager {
    private List<TrainingData> dataList;
    private IntegerProperty recordsCount = new SimpleIntegerProperty(0);

    public TrainingManager(){
        dataList = new ArrayList<>();
    }

    public void addData(TrainingData data){
        dataList.add(data);
        recordsCount.setValue(dataList.size());
    }

    public String getDataListString(){
        StringBuilder builder = new StringBuilder();
        builder.append(getTrainingSetParams()).append(System.lineSeparator());
        dataList.forEach(d->{
            builder.append(d.toString());
            builder.append(System.lineSeparator());
        });
        return builder.toString();
    }

    public IntegerProperty getRecordsCountProperty() {
        return recordsCount;
    }

    private String getTrainingSetParams(){
        return dataList.size()+" "+dataList.get(0).getSetSize()+" "+4;
    }
}
