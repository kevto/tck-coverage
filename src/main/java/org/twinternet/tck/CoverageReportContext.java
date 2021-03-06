package org.twinternet.tck;

import java.util.*;
import java.util.stream.Collectors;

final class CoverageReportContext {

    private final Set<Class<?>> registeredClasses;

    private final Map<String, Section> registeredSections;

    private final Set<String> registeredTestResultIds;

    private final List<TckTestResult> testResults;

    private CoverageReportContext() {
        this.registeredClasses = new HashSet<>();
        this.registeredSections = new HashMap<>();
        this.registeredTestResultIds = new HashSet<>();
        this.testResults = new ArrayList<>();
    }

    static CoverageReportContext of() {
        return new CoverageReportContext();
    }

    void registerClass(final Class<?> cls) {
        Objects.requireNonNull(cls);
        registeredClasses.add(cls);
    }

    boolean isClassRegistered(final Class<?> cls) {
        Objects.requireNonNull(cls);
        return registeredClasses.contains(cls);
    }
    
    boolean isSectionRegistered(final String id) {
        Objects.requireNonNull(id);
        return registeredSections.containsKey(id);
    }

    void registerSection(final Section section) {
        registeredSections.putIfAbsent(section.id().value(), section);
    }

    void addTestResult(final TckTestResult testResult) {
        if (registeredTestResultIds.contains(testResult.getId())) {
            System.err.println("Another test result object was registered with id: " + testResult.getId());
            return;
        }
        registeredTestResultIds.add(testResult.getId());
        testResults.add(testResult);
    }
    
    List<Section> getSections() {
        return registeredSections.values().stream()
                .collect(Collectors.toList());
    }

    List<TckTestResult> getTestResults() {
        return testResults;
    }
}
