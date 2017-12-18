＃adpater
Adapter是所有Adapter子类的父类，它是一个接口，里面定义了很多基本的方法。通常，Adapter是作为连接AdapterView（ListView...）和数据集的桥梁。它可以获取数据集合中的每一项并于AdapterView(ListView.....)相应的视图项一一对应起来，将数据显示。
Adapter主要方法解析：
int getCount():
获取与当前Adapter连接的数据集合的项数总和。

Object getItem(int Posistion):
获取与当前Adapter绑定的数据集中指定下标的项。

long getItemId(int position):
获取与当前Adapter绑定的数据集中指定下标的项所在的row id（即在list中的下标）

View getView(int position,View convertView,ViewGroup parent):
这个方法是实现Adapter子类的难点和最重要的地方，往往最容易出现问题的地方也是这里，所以要特别注意。这个方法目的在于获取一个用于展示当前数据集中指定下标的数据项的视图。通常有两种方法来获取View，自己创建一个View或者用XML布局填充来获得一个View。通常情况下，在填充一个View的时候，如果没有在LayoutInflater.inflate方法中指定View的根视图的话，会默认的将当前View附加到父视图（ListView....）中，并用默认的布局参数填充它。

重要参数说明：
convertView:这个参数在优化Adapter数据填充的效率方面是非常有用的。它表示一个可复用的旧视图，通常情况下，在使用它之前，需要先判断它是否为空，为空的话，需要先给他填充一个视图。不为空的话就可以复用了。其实在Adapter中，一个AdapterView填充数据的时候可以指定多个布局的（很多人以为只能是一种布局），所以在我们进行获取convertView显示数据的时候，应该配合getItemViewType方法来判断用于填充当前的数据项的视图是什么（如果只有一种视图，就没必要进行这些判断了）。只有这样才可以在合适的地方显示合适的数据。稍后会用详细的例子说明，暂时我们知道有这样的功能就行。
parent:当前的视图所依赖的父视图，默认会使用它提供的布局参数。

int getItemViewType(int position):
通过它返回的结果可以用来判断用于填充数据集中指定下标的数据项所使用的视图类型。此方法返回的值应该介于0-（getViewTypeCount返回的结果-1）之间。

int getViewTypeCount():
返回getView可能返回的视图的类型的总和，如果getView每次返回的结果都是一样的，这个方法返回1。adpterView视图有几个不一样的视图，这个方法就返回多少。

在Adapter接口中其实可以注册观察对象来观察当前Adapter所关联的数据集是否发生改变，当数据集发生变化时（包括数据集数据的改变或者数据集变得不可用），就会通知观察者，如果我们对数据集的变化高度敏感，就需要关注DataSetObserver这个抽象类，这里大概介绍一下，这个类是一个抽象类，定义了两个回调方法，在数据集发生改变时，会回调相关的方法，这两个方法分别是：onChanged,onInvalidated.前者在数据集发生变化时会被调用，比如重新查询了数据导致数据集合发生了改变，后者在数据集变的不可用的时候会被调用，比如Cursor数据源被关闭了。

Adapter有两个方法，可以进行注册观察者和解绑观察者。

void registerDataSetObserver(DataSetObserver observer):
给当前的Adapter注册一个observer对象，在绑定的数据集发生变化时进行回调。

void unregisterDataSetObserver(DataSetObserver observer):
解除之前在此Adapter中注册的observer的监听，之后将不再关注数据集变化。
