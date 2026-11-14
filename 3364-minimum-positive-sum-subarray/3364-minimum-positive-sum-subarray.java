
class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {

        int n = nums.size();

        int[] prefix = new int[n + 1];

        // Calculate prefix sum
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();

        int ans = Integer.MAX_VALUE;

        for (int j = l; j <= n; j++) {

            // Add prefix[j-l]
            int add = prefix[j - l];
            map.put(add, map.getOrDefault(add, 0) + 1);

            // Remove prefix[j-r-1]
            int removeIndex = j - r - 1;

            if (removeIndex >= 0) {
                int remove = prefix[removeIndex];

                if (map.get(remove) == 1) {
                    map.remove(remove);
                } else {
                    map.put(remove, map.get(remove) - 1);
                }
            }

            // Find greatest prefix sum smaller than prefix[j]
            Integer key = map.lowerKey(prefix[j]);

            if (key != null) {
                ans = Math.min(ans, prefix[j] - key);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
