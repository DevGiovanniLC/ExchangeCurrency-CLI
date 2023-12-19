package interfaces;

import java.util.Map;

public interface ReferenceRateLoader
{
     Map<String,Double> load(String date);
}
