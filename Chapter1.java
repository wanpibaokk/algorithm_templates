/**
 * 包括排序、二分、高精度、前缀和与差分、双指针算法、位运算、离散化、区间合并等内容。
 *
 * 快速排序模板
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

 * 归并排序模板
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

 * 整数二分模板 两种
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

 * 浮点数二分模板
 * 技巧：题目精度要求e-4，一般比题目要求高两位e-6
 double eps = 1e-6;
 while (r - l > eps) {
    double mid = (l + r) / 2;
    if (check(mid)) r = mid;
    else l = mid;
 }
 return l;

 */
public class Chapter1 {
    public static void main(String[] args) {

    }
}
