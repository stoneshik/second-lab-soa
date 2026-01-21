package lab.soa.presentation.params.sort;

import java.util.ArrayList;
import java.util.List;

import lab.soa.infrastructure.exceptions.IncorrectParamException;

public class SortParamParser {
    private SortParamParser() {}

    public static List<SortParam> parseSortStrings(List<String> sortStrings) {
        List<SortParam> sortParams = new ArrayList<>();
        if (sortStrings != null) {
            for (String sortStr : sortStrings) {
                if (sortStr != null && !sortStr.trim().isEmpty()) {
                    String[] parts = sortStr.split(",");
                    if (parts.length == 2) {
                        String field = parts[0].trim();
                        String direction = parts[1].trim().toLowerCase();
                        sortParams.add(new SortParam(field, direction));
                    } else {
                        throw new IncorrectParamException(
                            "Invalid sort parameter format: " + sortStr + ". Expected 'fieldName,direction'."
                        );
                    }
                }
            }
        }
        if (sortParams.isEmpty()) {
            sortParams.add(new SortParam("id", SortDirection.ASC.getValue()));
        }
        return sortParams;
    }
}
