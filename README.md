# NodeListView
接点ListView
废话不多说先来张图片


![image](https://github.com/LiLiTaBaBa/NodeListView/raw/master/kuaizhao.jpg)



具体的使用

private void initViews() {

//初始化控件

mNodeListView = (NodeListView) findViewById(R.id.nodeListView);

mList = new ArrayList();

//添加接点数据

mList.add(new NodeBean("南京市江宁区河定桥双龙大道119号", "金轮新都汇"));

mList.add(new NodeBean("南京市江宁区胜太路秦淮路11号", "大数据银坤"));

mList.add(new NodeBean("南京市江宁区河定桥金王府18号", "大数据银坤"));

mList.add(new NodeBean("南京市雨花台铁心桥江艺路25号", "大数据银坤"));

mList.add(new NodeBean("南京市雨花台铁心桥熙春路16号", "大数据银坤"));

mList.add(new NodeBean("南京市雨花台铁心桥双龙大道226号", "大数据银坤"));

mList.add(new NodeBean("南京市雨花台铁心桥汇智大道485号", "大数据银坤"));

mList.add(new NodeBean("南京市雨花台铁心桥双龙大道36号", "大数据银坤"));

mList.add(new NodeBean("南京市雨花台软件大道1118号", "大数据银坤"));

//设置数据

        mNodeListView.setDatas(mList);


  }
    
    
    
实现的比较简单，希望多多指教。
