package com.timvanx.biggerdvd.dvd;

import com.timvanx.biggerdvd.util.Constants;

import java.util.Scanner;


/**
 * 主菜单类(包含所有核心方法！)
 * @author TimVan
 * @date 2018年9月6日08:53:18
 */
public class Menu {
    //保存用户名
    private String userName = "用户";

    static {
        //初始化DVD集合DVDArr
        DVD dvd = new DVD();
    }

    /**
     * 系统中主菜单选择选项
     *
     * @return isEndProgram 退出UI界面循环标识
     */
    private boolean selectOption() {
        //退出UI界面循环标识
        boolean isProgramContinue = true;
        int inputNum = 0;
        //展示输入选项内容
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.getKey() + "、" + option.getValue());
        }
        System.out.print("请输入对应数字：");
        inputNum = Constants.scanfInt();

        switch (inputNum) {
            //显示DVD
            case 1:
                display();
                break;
            //查看DVD
            case 2:
                read();
                break;
            //借出DVD
            case 3:
                loan();
                break;
            //归还DVD
            case 4:
                returnDVD();
                break;
            //添加DVD
            case 5:
                add();
                break;
            //修改DVD
            case 6:
                update();
                break;
            //删除DVD
            case 7:
                delete();
                break;
            //退出
            case 0:
                isProgramContinue = false;
                break;
            default:
                System.out.println("数字不在范围内，请重新输入");
                break;
        }
        return isProgramContinue;
    }

    /**
     * 显示DVD
     */
    private void display() {
        System.out.print("BiggerDVD系统" + Constants.VERSION + "---->");
        System.out.println("显示当前所有DVD信息");
        System.out.println("编号\t\t名称\t\t\t\t状态");
        System.out.println("--\t\t--\t\t\t\t--");

        for (Object dvd : DVD.getDVDArr()) {
            System.out.println(((DVD) dvd).toString());
        }

        System.out.println("请输入数字0返回：");
        int inputNum = Constants.scanfInt();

    }

    /**
     * 查看DVD
     */
    private void read() {
        System.out.print("BiggerDVD系统" + Constants.VERSION + "---->");
        System.out.println("查询DVD信息");
        System.out.println("--------------------------");
        int inputNum = 0;
        //展示输入选项内容
        for (ReadMenuOption option : ReadMenuOption.values()) {
            System.out.println(option.getKey() + "、" + option.getValue());
        }
        System.out.print("请选择查询方式：");
        inputNum = Constants.scanfInt();

        if (inputNum == ReadMenuOption.ReadById.getKey()
                || inputNum == ReadMenuOption.ReadByName.getKey()){
            //退出查询循环的标识
            boolean isProgramContinue = true;
            while (isProgramContinue){
                if (inputNum == ReadMenuOption.ReadById.getKey()){
                    //按编号查询
                    readByID();
                }
                else {
                    //按名称查询
                    readByName();
                }
                //继续下一步或返回上一层
                isProgramContinue = Constants.nextOrBack();
            }
        }
        else {
            System.out.println("数字不在范围内，请重新输入");
        }

        System.out.println("--------------------------");


    }

    /**
     * 按编号查询-查看DVD
     */
    private void readByID(){
        System.out.println("请输入DVD的编号：");
        int inputNum = Constants.scanfInt();

        //是否找到的标识符
        boolean isExist = false;
        for (Object dvd : DVD.getDVDArr()) {
            if (((DVD) dvd).getId() == inputNum) {
                System.out.println("编号\t\t名称\t\t\t\t状态");
                System.out.println("--\t\t--\t\t\t\t--");
                System.out.println(dvd.toString());
                isExist = true;
            }
        }
        if (!isExist){
            System.out.println("对不起，你输入的DVD编号不存在！");
        }

    }

    /**
     * 按名称查询-查看DVD
     */
    private void readByName(){
        System.out.println("请输入DVD的精确名称（无书名号）：");
        Scanner scanner = new Scanner(System.in);
        String inputScanString = scanner.nextLine();
        String inputString = "《" + inputScanString + "》";

        //是否找到的标识符
        boolean isExist = false;
        for (Object dvd : DVD.getDVDArr()) {
            if (((DVD) dvd).getName().equals(inputString)) {
                System.out.println("编号\t\t名称\t\t\t\t状态");
                System.out.println("--\t\t--\t\t\t\t--");
                System.out.println(dvd.toString());
                isExist = true;
            }
        }
        if (!isExist){
            System.out.println("对不起，你输入的DVD名称不存在！");
        }
    }

    /**
     * 借出DVD
     */
    private void loan(){
        System.out.print("BiggerDVD系统" + Constants.VERSION + "---->");
        System.out.println("借出DVD信息");
        //退出借出循环的标识
        boolean isProgramContinue = true;
        while (isProgramContinue){
            System.out.println("请输入DVD的编号：");
            int inputNum = Constants.scanfInt();

            //是否找到的标识符
            boolean isExist = false;
            //是否未被借出的标识符
            boolean isCouldLoan = false;
            //保存用户想借的DVD名称
            String dvdName = "null";

            for (int i = 0; i < DVD.getDVDArr().size(); i++) {
                DVD dvd = (DVD) DVD.getDVDArr().get(i);

                //是否存在
                if (dvd.getId() == inputNum){
                    isExist = true;
                    dvdName = dvd.getName();
                    //是否未被借出
                    if (!dvd.isStatus()){
                        dvd.setStatus(true);
                        //在数据中更改dvd信息
                        DVD.updateDVDInfo(dvd);
                        System.out.println("恭喜！"+dvdName+"成功借出");
                        isCouldLoan = true;
                        break;
                    }
                }
            }
            //借入loan()和借出returnDVD()操作中的终结提示操作
            //借出returnDVD()操作中的终结提示操作
            if (!isExist){
                System.out.println("对不起，你输入的DVD编号不存在！");
            }
            else {
                if (!isCouldLoan){
                    System.out.println("对不起，"+dvdName+"已经被人借走了！");
                }
                //展示所有DVD信息
                display();
            }

            //继续下一步或返回上一层
            isProgramContinue = Constants.nextOrBack();

        }
    }

    /**
     * 归还DVD
     */
    private void returnDVD(){
        System.out.print("BiggerDVD系统" + Constants.VERSION + "---->");
        System.out.println("归还DVD信息");
        //退出借出循环的标识
        boolean isProgramContinue = true;
        while (isProgramContinue){
            System.out.println("请输入要归还的DVD编号：");
            int inputNum = Constants.scanfInt();
            System.out.println("请输入要归还的DVD精确名称");
            Scanner scanner = new Scanner(System.in);
            String inputScanString = scanner.nextLine();
            String inputString = inputScanString;

            //是否找到的标识符
            boolean isExist = false;
            //是否未被借出的标识符
            boolean isCouldLoan = false;
            //保存用户想借的DVD名称
            String dvdName = "null";

            for (int i = 0; i < DVD.getDVDArr().size(); i++) {
                DVD dvd = (DVD) DVD.getDVDArr().get(i);
                //是否存在
                if (dvd.getId() == inputNum &&
                        dvd.getName().equals(inputString)){
                    isExist = true;
                    dvdName = dvd.getName();
                    //是否未被借出
                    if (dvd.isStatus()){
                        dvd.setStatus(false);
                        //在数据中更改dvd信息
                        DVD.updateDVDInfo(dvd);
                        System.out.println("恭喜！"+dvdName+"成功归还");
                        isCouldLoan = true;
                        break;
                    }
                }
            }

            //借出returnDVD()操作中的终结提示操作
            if (!isExist){
                System.out.println("对不起，你输入的DVD编号不存在！");
            }
            else {
                if (!isCouldLoan){
                    System.out.println("对不起，"+dvdName+"还没被人借走！");
                }
                //展示所有DVD信息
                display();
            }
            //继续下一步或返回上一层
            isProgramContinue = Constants.nextOrBack();

        }



    }

    /**
     * 添加DVD
     */
    private void add() {
        System.out.print("BiggerDVD系统" + Constants.VERSION + "---->");
        System.out.println("添加DVD信息");
        //退出借出循环的标识
        boolean isProgramContinue = true;
        while (isProgramContinue) {

            System.out.println("请输入要添加的DVD名称（无书名号）：");
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine();
            DVD newDvd = new DVD(inputString);

            DVD.getDVDArr().add(newDvd);
            //数据中新增DVD信息
            DVD.addDVDInfo(newDvd);
            Integer newId = newDvd.getId();

            System.out.println("添加成功！新添加的DVD的编号为"
                    + newId);
            //展示所有DVD信息
            display();

            //继续下一步或返回上一层
            isProgramContinue = Constants.nextOrBack();
        }
    }

    /**
     * 修改DVD
     */
    private void update() {
        System.out.print("BiggerDVD系统" + Constants.VERSION + "---->");
        System.out.println("修改DVD信息");
        //退出借出循环的标识
        boolean isProgramContinue = true;
        while (isProgramContinue) {

            System.out.println("请输入原DVD名称：");
            Scanner scanner = new Scanner(System.in);
            String inputScanString = scanner.nextLine();
            String inputString = "《" + inputScanString + "》";
            System.out.println("请输入新DVD名称：");
            String newInputScanString = scanner.nextLine();
            String newInputString = newInputScanString;

            //是否找到的标识符
            boolean isExist = false;
            //是否未被修改的标识符
            boolean isCouldUpdate = false;

            for (int i = 0; i < DVD.getDVDArr().size(); i++) {
                DVD dvd = (DVD) DVD.getDVDArr().get(i);
                //是否存在
                if (dvd.getName().equals(inputString)) {
                    isExist = true;
                    //是否未被借出
                    if (!dvd.isStatus()) {
                        dvd.setName(newInputString);
                        //在数据中更改dvd信息
                        DVD.updateDVDInfo(dvd);
                        System.out.println("修改成功！新DVD名称为" + newInputString);
                        isCouldUpdate = true;
                        break;
                    }
                }
            }

            //借出returnDVD()操作中的终结提示操作
            if (!isExist) {
                System.out.println("对不起，你输入的DVD编号或名称不存在！");
            } else {
                if (!isCouldUpdate) {
                    System.out.println("对不起，" + inputString
                            + "还未归还，无法进行修改操作！");
                }
                //展示所有DVD信息
                display();
            }

            //继续下一步或返回上一层
            isProgramContinue = Constants.nextOrBack();

        }


    }

    /**
     * 删除DVD
     */
    private void delete() {
        System.out.print("BiggerDVD系统" + Constants.VERSION + "---->");
        System.out.println("删除DVD信息");
        //退出借出循环的标识
        boolean isProgramContinue = true;
        while (isProgramContinue) {
            System.out.println("请输入要删除的DVD编号：");
            int inputNum = Constants.scanfInt();
            System.out.println("请输入要删除的DVD名称（无书名号）：");
            Scanner scanner = new Scanner(System.in);
            String inputScanString = scanner.nextLine();
            String inputString = inputScanString;



            //是否找到的标识符
            boolean isExist = false;
            //是否未被修改的标识符
            boolean isCouldUpdate = false;

            for (int i = 0; i < DVD.getDVDArr().size(); i++) {
                DVD dvd = (DVD) DVD.getDVDArr().get(i);
                //是否存在
                if (dvd.getId() == inputNum &&
                        dvd.getName().equals(inputString)) {

                    isExist = true;
                    //是否未被借出
                    if (!dvd.isStatus()) {
                        DVD.getDVDArr().remove(i);
                        //使用数据库删除DVD信息（通过主键ID）
                        DVD.deleteDVDInfo(inputNum);
                        System.out.println(inputString + "删除成功");
                        isCouldUpdate = true;
                        break;
                    }
                }
            }

            //借出returnDVD()操作中的终结提示操作
            if (!isExist) {
                System.out.println("对不起，你输入的DVD编号或名称不存在！");
            } else {
                if (!isCouldUpdate) {
                    System.out.println("对不起，" + inputString
                            + "还未归还，无法进行删除操作！");
                }
                //展示所有DVD信息
                display();
            }
            //继续下一步或返回上一层
            isProgramContinue = Constants.nextOrBack();

        }


    }

    /**
     * 入口
     */
    public void init(String userName) {
        System.out.println("********** " + userName
                + "你好！欢迎进入系统 **************");
        Menu menu = new Menu();
        while (menu.selectOption()) {
        }
        System.out.println("** 退出登录 **");

    }

    public static void main(String... args) {
        Menu menu = new Menu();
        menu.init("用户");
    }

}


/**
 * 界面选项中数字-内容
 */
enum MenuOption {
    Display(1, "显示DVD"), Read(2, "查看DVD"), Loan(3, "借出DVD"), Return(4, "归还DVD"), add(5, "添加DVD"), update(6, "修改DVD"), delete(7, "删除DVD"), Logout(0, "注销");
    private final int key;
    private final String value;

    MenuOption(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }


}

/**
 * 【查询】中数字-内容
 */
enum ReadMenuOption {
    ReadById(1, "按编号查询"), ReadByName(2, "按名称查询");
    private final int key;
    private final String value;

    ReadMenuOption(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}



