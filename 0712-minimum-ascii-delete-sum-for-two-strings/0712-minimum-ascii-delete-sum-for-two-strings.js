/**
 * @param {string} s1
 * @param {string} s2
 * @return {number}
 */
var minimumDeleteSum = function(s1, s2) {
    const m = s1.length, n = s2.length;

    dp = new Array(n + 1);

    dp[0] = 0;

    for (let j = 1; j <= n; j += 1) {
        dp[j] = dp[j - 1] + s2.charCodeAt(j - 1);
    }

    for (let i = 1; i <= m; i += 1) {
        let prev = dp[0];
        const lastS1 = s1.charCodeAt(i - 1);
        dp[0] += lastS1;

        for (let j = 1; j <= n; j += 1) {
            const temp = dp[j];
            const lastS2 = s2.charCodeAt(j - 1);
            if (lastS1 === lastS2)
                dp[j] = prev;
            else
                dp[j] = Math.min(dp[j] + lastS1, dp[j - 1] + lastS2);
            prev = temp;
        }
    }

    return dp[n]
};
