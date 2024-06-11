# Library-management-system

#### 介绍
基于MySQL+SWING的图书借阅平台

#### 软件架构
软件架构说明
大二时从0开始自己完完整整写的第一个项目，当时并没有系统的学习js/HTML前端多件套的情况下用了最原始的SWING UI图形界面，也参考了许多github/TensorFlow等多个平台的优秀项目的设计模式、开发思路，在没有系统化的学习软件设计体系也通过该项目的开发自己摸索出了基于功能模块开发的软件设计模式与思路，并通过绘画一系列时序图、流程图、功能模块图应用于该项目之中

该项目于2021年六月份正式结束，至今已经过去了好多年，遂push该项目以此留念 以及给广大学习的开发者一个从零开始开发一个系统的一个参考



#### 软件开发日志 :memo: 
 **4/18** 
添加冻结用户功能√——>添加注销用户功能√——>添加修改密码功能√——>管理员用户界面设计
 ——>管理图书界面设计、功能实现（增删改查）——>管理用户功能(增删改查)——>读者界面/读者借
书界面设计——>借书功能实现（是否借阅图书、是否归还、日期类、）

去重：目标：防止出现用户名一样或者ID一样的用户 今日已完成√


 **4/27** 
功能模块化：DAO、interface、implement(实现主要的功能-增删改查询图书、用户) 
查询多条记录：运用泛型、哈希表的数据结构
dbutils:做新的功能界面时用，如update、query....

DAO:通用的增删改方法、查询方法-获取一条或多条记录
BooksDAO:
addBook():增加一本书

5.1
实现增添书籍
实现将数据库中所有书籍罗列到表格中，选中表格中的一条则可以进行删除、修改  

增添图书√
罗列√
选中表格中的记录进行删除，修改

读取书本类型，并根据不同字段进行分类
SELECT  DISTINCT TYPE 种类 FROM `books_info` √


5/5

```
public void checkAndAddNewType() {
		String s = typetextField.getText();
		int co = comboBox.getItemCount();
		for(int i=0;i<co;i++) {
			String item = comboBox.getItemAt(i).toString();
			if(s.equalsIgnoreCase(item)) {
				break;
			}else {
				if(i==(co-1)) {
					System.out.println("新增图书类型为："+s);
					comboBox.addItem(s);
				}
			}
		}
	}
```


提升了新增图书种类的便捷性

删除图书：当连续使用删除功能两次时只删掉了一本？ 已解决√

5/9

```
if(!(as.equals(""))) {
			amount = Integer.parseInt(as);
		}else {
			amount = 0;
		}
```


查询书籍：可根据书本类型进行分类
已修正√
目前问题：分类后选择修改删除仍然是第一条的记录
已修正 √	
List listSel;
			
```
if(!(comboBox.getSelectedItem().toString().equalsIgnoreCase("全部书籍"))) {
				listSel = typeColList;
			}else {
				listSel = list;
			}
```


设计普通用户的界面：

 **5/11** 

正在实现：选中一个图书节点时，显示出该节点信息 已实现√
已实现：读者基本查书栏


5/16
15:43 搜索功能终于完善......


```

UPDATE `userinfo` SET `record`= CONCAT(`record`,"\n","呵1") WHERE `user_Name`="234324"

UPDATE `userinfo` SET `record`= CONCAT(`record`,"\n",?) WHERE `user_Name`=?
```


设计借阅功能
每个读者借了一本书后便不能再次借阅该书，直到已经归还该书（ Boolean isBack ?）

--------

```
UPDATE `userinfo` SET `record`= CONCAT(`record`,"\n",?) WHERE `user_id`= ? ;
UPDATE `userinfo` SET `owned_bk`= CONCAT(`owned_bk`,"\n", ?) WHERE `user_id`=?

```


SELECT CONCAT_WS(',1','11','22',NULL)
owned_bk 如何删减？ update( )?
如何展示owned_bk中的每一项记录？

管理员：在借书记录中展示每一位读者的所有借书记录
每条记录展示是否归还图书？

借书记录中选出所有未归还记录中超过特定时段（1天，5天....）未归还记录生成摧还名单
管理员则可以发送催还消息，读者登录后就可以收到该条消息

12:24：修改图书功能偶遇BUG? 连续操作修改图书类型时会报错
更新功能：模糊查询读者 √

借阅书籍问题：如何将每个用户的每条借阅记录分开，最后罗列到一张表上？
方案（未实现）：1（未实现）、通过sql语句定义函数/存储过程，将单个用户的借阅记录分段到同一张表上的多条记录，方便接下来的实现操作
2（较易，实现中）、建立一张新表，将书名，借阅时间、借阅者、归还状态在调用借书、还书功能时进行同步记录，进行管理操作时也可同步

图书记录也许可以根据未归还和已归还进行分类？筛选出未归还的记录形成催交名单
`SELECT `borrower`, `borrowed_book`,`borrow_date`,`isReturn` FROM `borrowrecord` WHERE `borrower` LIKE "%(%"`

5/24
个人借阅图书表格初步完成√
读书笔记完成√

图书维护的相关BUG:更改图书类别成功会出现新的一条相关记录

5/24的BUG已解决：通过固定索引使得点击修改后 后来的选中项不会被修改
归还图书（）：图书归还索引尚且只有书名，不明确（万一有多本书名一样的书呢？）
又出现BUG:删除图书报错？

5/31 更新：可查询历史借阅记录了

6/2


```
UPDATE `userinfo`
SET `owned_bk`=REPLACE(`owned_bk`,'呵呵','')
WHERE `user_id`='04191918'
```


6/3 更新了判断是否借阅该书、修改了还书功能中的移除字段
修改了没有读取到用户密码、持有书籍的BUG

待修改：对借阅记录中已归还\待归还进行分类

6/4 
读取生成的图片
1生成
2保存
3读取
4展示

 **6/5** 
对于借阅日期进行分类，统计每个借阅日期的借阅量


```
UPDATE `userinfo` SET `borrow_time`=0
置空一整列

SELECT COUNT(borrow_date) FROM `borrowrecord` GROUP BY `borrow_date`
分类

UPDATE `books_info` SET `getbor_Times`=5

UPDATE `userinfo` SET `borrow_time`=4

SELECT `getbor_Times`,`book_name` FROM `books_info` ORDER BY `getbor_Times`;
```


 **6/7优化：** 
1.改进了读者排行榜排序位次
2.改进了最佳借阅量图书榜排序位次
3.优化了借阅表部分展示


```
SELECT `getbor_Times`,`book_name` FROM `books_info` ORDER BY `getbor_Times` DESC

SELECT `borrow_time` ,`user_Name` FROM `userinfo` ORDER BY `borrow_time` DESC

6.11
SELECT COUNT(borrow_date),`borrow_date` FROM `borrowrecord` GROUP BY `borrow_date` ORDER BY `borrow_date` ASC 



```


#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
