package view;

import java.util.Map;

public interface ReferenceRateLoader
{
     Map<String,Double> load(String date);
}
