import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Tester {
    private ArrayList<Boolean> results;
    private int maxScore;

    public Tester(int maxScore) {
        results = new ArrayList<>();
        this.maxScore = maxScore;
    }

    public Tester isInterface(String[] typeNames) {
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

    public Tester isAbstractClass(String[] typeNames) {
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

    public Tester isClass(String[] typeNames) {
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

    public Tester isEnum(String[] typeNames) {
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

    public Tester extendsClass(String parentName, String[] childNames) {
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

    public Tester implementsInterface(String parentName, String[] childNames) {
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

    public Tester hasDeclaredField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();

            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName())) {
                        predicate = true;
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

    public Tester hasDeclaredPublicField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();

            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName()) && Modifier.isPublic(field.getModifiers())) {
                        predicate = true;
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

    public Tester hasDeclaredProtectedField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();

            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName()) && Modifier.isProtected(field.getModifiers())) {
                        predicate = true;
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

    public Tester hasDeclaredPrivateField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();

            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName()) && Modifier.isPrivate(field.getModifiers())) {
                        predicate = true;
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

    public Tester hasDeclaredPackagePrivateField(String typeName, String[] fieldNames) {
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

    public Tester inheritsField(String parentType, String childType, String[] fieldNames) {
        Class<?> parentClazz, childClazz;
        boolean predicate1 = false, predicate2 = true;
        try {
            parentClazz = Class.forName(parentType);
            childClazz = Class.forName(childType);

            Field[] parentFields = parentClazz.getDeclaredFields();
            Field[] childFields = childClazz.getDeclaredFields();

            for (String fieldName : fieldNames) {
                for (Field parentField : parentFields) {
                    if (fieldName.equals(parentField.getName())) {

                        System.out.println(fieldName + " 111");

                        for (Field childField : childFields) {
                            if (fieldName.equals(childField.getName())) {
                                predicate2 = false;
                            }
                        }
                        if (predicate2) {
                            results.add(true);
                        } else {
                            results.add(false);
                        }
                        predicate1 = true;
                        predicate2 = true;
                    } else {
                        results.add(false);

                        System.out.println(fieldName + " 222");

                    }
                }
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

}
