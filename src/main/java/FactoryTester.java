import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

public class FactoryTester {
    private ArrayList<Boolean> results;
    private int maxScore;

    public FactoryTester(int maxScore) {
        results = new ArrayList<>();
        this.maxScore = maxScore;
    }

    public FactoryTester isInterface(String[] typeNames) {
        Class<?> clazz;
        boolean predicate;
        for(String typeName : typeNames) {
            try {
                clazz = Class.forName(typeName);
                predicate = clazz.isInterface();
                results.add(predicate);
            } catch (ClassNotFoundException e) {
                results.add(false);
            }
        }
        return this;
    }

    public FactoryTester isAbstractClass(String[] typeNames) {
        Class<?> clazz;
        boolean predicate;
        for (String typeName : typeNames) {
            try {
                clazz = Class.forName(typeName);
                predicate = Modifier.isAbstract(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers());
                results.add(predicate);
            } catch (ClassNotFoundException e) {
                results.add(false);
            }
        }
        return this;
    }

    public FactoryTester isClass(String[] typeNames) {
        Class<?> clazz;
        boolean predicate;
        for (String typeName : typeNames) {
            try {
                clazz = Class.forName(typeName);
                predicate = !Modifier.isAbstract(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers());
                results.add(predicate);
            } catch (ClassNotFoundException e) {
                results.add(false);
            }
        }
        return this;
    }

    public FactoryTester isEnum(String[] typeNames) {
        Class<?> clazz;
        boolean predicate;
        for (String typeName : typeNames) {
            try {
                clazz = Class.forName(typeName);
                predicate = clazz.isEnum();
                results.add(predicate);
            } catch (ClassNotFoundException e) {
                results.add(false);
            }
        }
        return this;
    }

    public FactoryTester extendsClass(String parentName, String[] childNames) {
        Class<?> childClazz, parentClazz;
        boolean predicate;
        for (String childName : childNames) {
            try {
                parentClazz = Class.forName(parentName);
                childClazz = Class.forName(childName);
                predicate = childClazz.getSuperclass().equals(parentClazz);
                results.add(predicate);
            } catch (ClassNotFoundException e) {
                results.add(false);
            }
        }
        return this;
    }

    public FactoryTester implementsInterface(String parentName, String[] childNames) {
        Class<?> childClazz, parentClazz;
        boolean predicate;
        for (String childName : childNames) {
            try {
                parentClazz = Class.forName(parentName);
                childClazz = Class.forName(childName);
                predicate = parentClazz.isAssignableFrom(childClazz) && parentClazz.isInterface();
                results.add(predicate);
            } catch (ClassNotFoundException e) {
                results.add(false);
            }
        }
        return this;
    }

    public FactoryTester hasDeclaredField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName())) {
                        predicate = true;
                        break;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasDeclaredPublicField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName()) && Modifier.isPublic(field.getModifiers())) {
                        predicate = true;
                        break;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasDeclaredProtectedField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName()) && Modifier.isProtected(field.getModifiers())) {
                        predicate = true;
                        break;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasDeclaredPrivateField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName()) && Modifier.isPrivate(field.getModifiers())) {
                        predicate = true;
                        break;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasDeclaredPackagePrivateField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName())
                            && !Modifier.isPublic(field.getModifiers())
                            && !Modifier.isProtected(field.getModifiers())
                            && !Modifier.isPrivate(field.getModifiers())) {
                        predicate = true;
                        break;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasDeclaredStaticField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName()) && Modifier.isStatic(field.getModifiers())) {
                        predicate = true;
                        break;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasDeclaredFinalField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName()) && Modifier.isFinal(field.getModifiers())) {
                        predicate = true;
                        break;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester inheritsField(String parentType, String childType, String[] fieldNames) {
        Class<?> parentClazz, childClazz;
        try {
            parentClazz = Class.forName(parentType);
            childClazz = Class.forName(childType);
            Field[] parentFields = parentClazz.getDeclaredFields();
            Field[] childFields = childClazz.getDeclaredFields();
            for (String fieldName : fieldNames) {
                if (isFieldInParent(fieldName, parentFields) && isFieldInChild(fieldName, childFields)) {
                    results.add(true);
                } else {
                    results.add(false);
                }
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasDeclaredMethod(String typeName, String[] methodNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (String methodName : methodNames) {
                for (Method method : methods) {
                    if (methodName.equals(method.getName())) {
                        predicate = true;
                        break;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasDeclaredPublicMethod(String typeName, String[] methodNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (String methodName : methodNames) {
                for (Method method : methods) {
                    if (methodName.equals(method.getName()) && Modifier.isPublic(method.getModifiers())) {
                        predicate = true;
                        break;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasDeclaredProtectedMethod(String typeName, String[] methodNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (String methodName : methodNames) {
                for (Method method : methods) {
                    if (methodName.equals(method.getName()) && Modifier.isProtected(method.getModifiers())) {
                        predicate = true;
                        break;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasDeclaredPrivateMethod(String typeName, String[] methodNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (String methodName : methodNames) {
                for (Method method : methods) {
                    if (methodName.equals(method.getName()) && Modifier.isPrivate(method.getModifiers())) {
                        predicate = true;
                        break;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasDeclaredPackagePrivateMethod(String typeName, String[] methodNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (String methodName : methodNames) {
                for (Method method : methods) {
                    if (methodName.equals(method.getName())
                            && !Modifier.isPublic(method.getModifiers())
                            && !Modifier.isProtected(method.getModifiers())
                            && !Modifier.isPrivate(method.getModifiers())) {
                        predicate = true;
                        break;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasDeclaredStaticMethod(String typeName, String[] methodNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (String methodName : methodNames) {
                for (Method method : methods) {
                    if (methodName.equals(method.getName()) && Modifier.isStatic(method.getModifiers())) {
                        predicate = true;
                        break;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasDeclaredFinalMethod(String typeName, String[] methodNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (String methodName : methodNames) {
                for (Method method : methods) {
                    if (methodName.equals(method.getName()) && Modifier.isFinal(method.getModifiers())) {
                        predicate = true;
                        break;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester inheritsMethod(String parentType, String childType, String[] methodNames) {
        Class<?> parentClazz, childClazz;
        try {
            parentClazz = Class.forName(parentType);
            childClazz = Class.forName(childType);
            Method[] parentMethods = parentClazz.getDeclaredMethods();
            Method[] childMethods = childClazz.getDeclaredMethods();
            for (String methodName : methodNames) {
                if (isMethodInClass(methodName, parentMethods) && !isMethodInClass(methodName, childMethods)) {
                    results.add(true);
                } else {
                    results.add(false);
                }
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester overridesMethod(String parentType, String childType, String[] methodNames) {
        Class<?> parentClazz, childClazz;
        try {
            parentClazz = Class.forName(parentType);
            childClazz = Class.forName(childType);
            Method[] parentMethods = parentClazz.getDeclaredMethods();
            Method[] childMethods = childClazz.getDeclaredMethods();
            for (String methodName : methodNames) {
                if (isMethodInClass(methodName, parentMethods) && isMethodInClass(methodName, childMethods)) {
                    results.add(true);
                } else {
                    results.add(false);
                }
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasFieldType(String typeName, String fieldName, Class<?> fieldType) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName()) && field.getType().equals(fieldType)) {
                    predicate = true;
                    break;
                }
            }
            if (predicate) {
                results.add(true);
            } else {
                results.add(false);
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasMethodReturnType(String typeName, String methodName, Class<?> returnType) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName()) && method.getReturnType().equals(returnType)) {
                    predicate = true;
                    break;
                }
            }
            if (predicate) {
                results.add(true);
            } else {
                results.add(false);
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public FactoryTester hasDeclaredConstructor(String typeName, Class<?>[] parameterTypes) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                Class<?>[] types = constructor.getParameterTypes();
                if(Arrays.equals(types, parameterTypes)) {
                    predicate = true;
                    break;
                }
            }
            if (predicate) {
                results.add(true);
            } else {
                results.add(false);
            }
        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public double calculate() {
        int result = 0;
        for (boolean value : results) {
            if (value) {
                result = result + 1;
            }
        }
        double normalizeResult = (double)result * maxScore / results.size();
        return new BigDecimal(normalizeResult).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private boolean isFieldInParent(String fieldName, Field[] parentFields) {
        for (Field field : parentFields) {
            if (fieldName.equals(field.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isFieldInChild(String fieldName, Field[] childFields) {
        for (Field field : childFields) {
            if (fieldName.equals(field.getName())) {
                return false;
            }
        }
        return true;
    }

    private boolean isMethodInClass(String methodName, Method[] parentMethods) {
        for (Method method : parentMethods) {
            if (methodName.equals(method.getName())) {
                return true;
            }
        }
        return false;
    }
}
