
* 包括排序、二分、高精度、前缀和与差分、双指针算法、位运算、离散化、区间合并等内容。

* 快速排序模板
```java
void quickSort(int[] nums, int l, int r) {
    if (l >= r) return;
    int pivot = random.nextInt(r - l + 1) + l;
    swap(nums, pivot, l);
    int x = nums[l], i = l - 1, j = r + 1;
    while (i < j) {
        do i++; while (nums[i] < x);
        do j--; while (nums[j] > x);
        if (i < j) swap(nums, i, j);
    }
    quickSort(nums, l, j);
    quickSort(nums, j + 1, r);
}
```
* 归并排序模板
```java
int[] tmp; // 和nums一样大的
void mergeSort(int[] nums, int l, int r) {
    if (l >= r) return;
    int mid = l + r >> 1;
    mergeSort(nums, l, mid);
    mergeSort(nums, mid + 1, r);
    int k = 0, i = l, j = mid + 1;
    while (i <= mid && j <= r) {
        if (nums[i] <= nums[j]) tmp[k++] = nums[i++];
        else tmp[k++] = nums[j++];
    }
    while (i <= mid) tmp[k++] = nums[i++];
    while (j <= r) tmp[k++] = nums[j++];
    for (i = l, j = 0; i <= r; i++, j++) nums[i] = tmp[j];
}
```
* 整数二分模板 两种
```java
while (l < r) {
int mid = l + r >> 1;
if (check(mid)) r = mid;
else l = mid + 1;
}
return l;
while (l < r) {
int mid = l + r + 1 >> 1;
if (check(mid)) l = mid;
else r = mid - 1;
}
return l;
```
* 浮点数二分模板
技巧：题目精度要求e-4，一般比题目要求高两位e-6
```java
double eps = 1e-6;
while (r - l > eps) {
double mid = (l + r) / 2;
if (check(mid)) r = mid;
else l = mid;
}
return l;
```
* 高精度：由于数字位数过多超过了一般数据类型可存的长度

一般使用数组来高精度的表示这个超长的数字

主要涉及四类情况：

高精度加法：两个高精度数字相加

高精度减法：两个高精度数字相减

高精度乘低精度：一个用数组表示，一个用int表示

高精度除以低精度：一个用数组表示，一个用int表示
* 高精度加法
```java
List<Integer> add(List<Integer> A, List<Integer> B) {
    int n = A.size(), m = B.size();
    List<Integer> C = new ArrayList<>();
    for (int i = 0, t = 0; i < n || i < m || t > 0; i++) {
        if (i < n) t += A.get(i);
        if (i < m) t += B.get(i);
        C.add(t % 10);
        t /= 10;
    }
    return C;
}
```
* 高精度减法

先判断AB谁更大，模板默认 A > B > 0

结果可能存在前导零  123 - 120 = 003
```java
boolean cmp(List<Integer> A, List<Integer> B) {
    int n = A.size(), m = B.size();
    if (n != m) return n > m;
    for (int i = n - 1; i >= 0; i--) {
        if (A.get(i) != B.get(i)) {
        return A.get(i) > B.get(i);
    }
    }
    return true;
}

List<Integer> sub(List<Integer> A, List<Integer> B) {
    int n = A.size(), m = B.size();
    List<Integer> C = new ArrayList<>();
    for (int i = 0, t = 0; i < n; i++) {
        t += A.get(i);
        if (i < m) t -= B.get(i);
        C.add((t + 10) % 10);
        if (t < 0) t = -1;
        else t = 0;
    }
    return C;
}
```
* 高精度乘低精度
```java
List<Integer> mul(List<Integer> A, int b) {
    int n = A.size();
    List<Integer> C = new ArrayList<>();
    for (int i = 0, t = 0; i < n || t > 0; i++) {
        if (i < n) t += A.get(i) * b;
        C.add(t % 10);
        t /= 10;
    }
    return C;
}
```
* 高精度除以低精度

结果可能存在前导零，并且要注意结果顺序和另外三种情况相反

r为余数，初始为0

java是值传递，所以余数不好返回，做题直接写在main处理
```java
List<Integer> div(List<Integer> A, int b, int r) {
    int n = A.size();
    List<Integer> C = new ArrayList<>();
    for (int i = n - 1; i >= 0; i--) {
        r = r * 10 + A.get(i);
        C.add(r / b);
        r %= b;
    }
}
```
* 前缀和（一维、二维）、差分（一维和二维）
* 一维前缀和
```java
int[] sum = new int[n + 1];
for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];
```
* 二维前缀和
```java
int[][] sum = new int[n + 1][m + 1];
for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= m; j++) {
    sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + nums[i - 1][j - 1];
    }
}
```
* 一维差分
```java
int[] diff = new int[n + 1];
// [l, r]范围增加c
diff[l] += c;
diff[r + 1] -= c;
```
* 二维差分
```java
int[][] diff = new int[n + 5][m + 5]
// x1, y1  到  x2, y2 矩形范围内增加c
diff[x1][y1] += c;
diff[x2 + 1][y1] -= c;
diff[x1][y2 + 1] -= c;
diff[x2 + 1][y2 + 1] += c;
```
* 位运算

lowbit的作用：100010 得到 10

应用：树状数组    求二进制中1的个数
```java
lowbit(x) {
return x & (-x);
}
```
求二进制中1的个数也可以用Brian Kernighan算法

x &= (x - 1) 消去最后的1

* 离散化

特点：范围很大，但是值很稀疏

由于范围很大，无法直接创建那么大的数组，所以需要离散化，将值映射到更小的范围

思路：去重，排序。然后通过二分找到位置，位置即为离散化后的值。
```java
list = new ArrayList<>(new HashSet<>(list));
Collections.sort(list);

int find(List<Integer> list, int x) {
    int l = 0, r = list.size() - 1;
    while (l < r) {
        int m = l + r >> 1;
        if (list.get(m) >= x) r = m;
        else l = m + 1;
    }
    return l;
}
```