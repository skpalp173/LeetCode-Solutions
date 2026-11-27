class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] bonus = new int[n];

        // Calculate free copies
        for (int i = 0; i < n; i++) {
            int factor = items[i][0];

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % factor == 0) {
                    bonus[i]++;
                }
            }
        }

        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int freeItems = bonus[i];

            int[] activated = new int[budget + 1];

            // Buy first copy (activates free items)
            for (int b = price; b <= budget; b++) {
                activated[b] = dp[b - price] + 1 + freeItems;
            }

            // Buy additional copies
            for (int b = price; b <= budget; b++) {
                activated[b] = Math.max(
                    activated[b],
                    activated[b - price] + 1
                );
            }

            // Update DP
            for (int b = 0; b <= budget; b++) {
                dp[b] = Math.max(dp[b], activated[b]);
            }
        }

        return dp[budget];
    }
}