package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        String[] arr = signatureString.split(" ");
        List<MethodSignature.Argument> listArgs = parseArguments(signatureString);

        MethodSignature methodSignature = new MethodSignature("2", listArgs);
        String modeRegex = "public|private|protected";
        Pattern pattern = Pattern.compile(modeRegex);
        Matcher matcher = pattern.matcher(signatureString);

        if (matcher.find()) {
            methodSignature.setAccessModifier(matcher.group().trim());
            methodSignature.setReturnType(arr[1]);
            methodSignature.setMethodName(extractMethodName(arr[2]));
        } else {
            methodSignature.setAccessModifier(null);
            methodSignature.setReturnType(arr[0]);
            methodSignature.setMethodName(extractMethodName(arr[1]));
        }

        return methodSignature;
    }

    private List<MethodSignature.Argument> parseArguments(String signatureString) {
        List<MethodSignature.Argument> listArgs = new ArrayList<>();
        int i = signatureString.indexOf('(');
        int b = signatureString.indexOf(')');

        if (b - i > 1) {
            String s = signatureString.substring(i + 1, b);
            String[] arrs = s.split(", ");
            for (String st : arrs) {
                String[] arr1 = st.split(" ");
                listArgs.add(new MethodSignature.Argument(arr1[0], arr1[1]));
            }
        }

        return listArgs;
    }

    private String extractMethodName(String methodDeclaration) {
        int a = methodDeclaration.indexOf('(');
        return methodDeclaration.substring(0, a);
    }
}