public abstract class Person {
    public String name;
    public int age;
    public String gender;
    public String birthdate;
    public String address;

    public Person(String name, int age, String gender, String birthdate, String address) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthdate = birthdate;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
