package com.solarity.mavenproject2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author redlo
 */
public class FilterHandler {

    private Map<String, Set<FilterOption>> markers;

    private Map<String, Set<FilterOption>> heap;

    private void populateMarkers(Map<String, String> input) {
        if (input != null) {
            if (markers == null) {
                markers = new HashMap<>();
            }
            input.forEach((key, value) -> {
                addMarker(key, Arrays.asList(value.split(",")));
            });
        }
    }

    private boolean addMarker(String key, Object value) {
        if (markers != null && key != null && value != null) {
            if (value instanceof FilterOption) {
                return addOrCreateMarker(key, ((FilterOption) value));
            }
            if (value instanceof String) {
                return addOrCreateMarker(key, new FilterOption(value));
            }

            return ((List<Object>) value).stream()
                    .map(p -> new FilterOption(
                    p))
                    .anyMatch(e -> addOrCreateMarker(key, e));
        }
        return false;
    }

    private boolean addOrCreateMarker(String key, FilterOption option) {
        if (key != null && option != null) {
            if (markers.containsKey(key)) {
                Set<FilterOption> options = markers.get(key);
                if (options.isEmpty()) {
                    return options.add(option);
                } else {
                    FilterOption entity = options.stream()
                            .filter(o -> option.getValue().equals(o.getValue()))
                            .findAny()
                            .orElse(null);
                    if (entity != null) {
                        entity.setValue(option.getValue());//и.т.д
                    }
                }
            } else {
                Set<FilterOption> set = new HashSet<>();
                set.add(option);
                markers.put(key, set);
                return true;
            }
        }
        return false;
    }

    private boolean isOrigin(String name, FilterOption option) {
        if (markers != null && option != null) {
            if (markers.containsKey(name)) {
                return markers.get(name).stream()
                        .anyMatch(p -> p.getValue().equals(option.getValue()));
            }
            return false;
        }
        return false;
    }

    private boolean isEquals(String name, FilterOption option) {
        if (markers != null && option != null) {
            if (markers.containsKey(name)) {
                return markers.get(name).stream()
                        .anyMatch(p -> p.equals(option));
            }
        }
        return false;
    }

    public void initChild(FilterDescription description) {
        if (description != null) {
            String name = description.getName();
            Set<Object> sameValues = new HashSet<>();
            Set<Object> equalsSet = new HashSet<>();
            boolean flag = checkCurrentMarker(name);//при удалении true
            Set<FilterOption> setOfMarkers = markers.get(name);
            boolean checkFlag = setOfMarkers != null && !setOfMarkers.isEmpty();
            for (FilterOption option : description.getOptions()) {
                if (checkFlag) {
                    if (!sameValues.contains(option.getValue())) {
                        if (isOrigin(name, option)) {
                            sameValues.add(option.getValue());
                        }
                    }
                }
                if (findDependency(option)) {
                    if (addMarker(name, option)) {
                        flag = true;
                    }
                    addToHeap(name, option);
                } else {
                    removeFromHeap(name, option);
                }
                if (checkFlag) {
                    if (!equalsSet.contains(option.getValue())) {
                        if (isEquals(name, option)) {
                            equalsSet.add(option.getValue());
                        }
                    }
                }
            }

            if (checkFlag) {
                for (FilterOption marker : setOfMarkers) {
                    if (sameValues.contains(marker.getValue())
                            && !equalsSet.contains(marker.getValue())) {
                        removeFromMarkers(name, marker);
                        flag = true;
                    }
                }
            }

            if (flag) {
                initChild(description);
            }
        }
    }

    private boolean checkCurrentMarker(String name) {
        if (markers != null) {
            if (markers.containsKey(name)) {
                return markers.get(name)
                        .removeIf(marker -> !findDependency(marker));
            }
        }
        return false;
    }

    private boolean findDependency(FilterOption option) {
        if (markers != null && option != null) {
            return true;
        }
        return false;
    }

    private void addToHeap(String name, FilterOption option) {
        if (heap != null && option != null) {
            if (heap.containsKey(name)) {
                heap.get(name).add(option);
            } else {
                Set<FilterOption> set = new HashSet<>();
                heap.put(name, set);
            }
        }
    }

    private void removeFromHeap(String name, FilterOption option) {
        if (heap != null) {
            if (heap.containsKey(name)) {
                heap.get(name).remove(option);
            }
        }
    }

    private void removeFromMarkers(String name, FilterOption option) {
        if (markers != null) {
            if (markers.containsKey(name)) {
                markers.get(name).remove(option);
            }
        }
    }

}
