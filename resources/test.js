'use strict';

/**
 * @file PhantomJS setup.
 * @author Eric Weinstein <eric.q.weinstein@gmail.com>
 */

// Console output. We can't use .bind() here
// (see phantom-shims.js), so we have to get
// creative (read: dumb). (EQW 25 Sep 2015)
var COLORS = {
  error: '\x1b[31m',
  info: '\x1b[35m',
  ok: '\x1b[32m',
  reset: '\x1b[0m',
  warn: '\x1b[33m'
};

function error(msg) {
  console.log(COLORS.error + '[!] ' + msg + COLORS.reset);
}

function info(msg) {
  console.log(COLORS.info + '[+] ' + msg + COLORS.reset);
}

function ok(msg) {
  console.log(COLORS.ok + '[✓] ' + msg + COLORS.reset);
}

function warn(msg) {
  console.log(COLORS.warn + '[-] ' + msg + COLORS.reset);
}

var page = require('webpage').create();
var url = phantom.args[0];

page.onConsoleMessage = function (message) {
  console.log(message);
};

function exit(code) {
  setTimeout(function () { phantom.exit(code); }, 0);
  phantom.onError = function () {};
}

info('Loading URL: ' + url);

page.open(url, function (status) {
  if (status !== 'success') {
    error('Failed to open ' + url);
    phantom.exit(1);
  }

  info('Running tests.');

  var failures = page.evaluate(function () {
    konami_cljs.test.test_runner.runner();
    return window['test-failures'];
  });

  if (failures === 0) {
    ok('Tests succeeded.');
  } else {
    error('Tests failed!');
  }

  phantom.exit(failures ? 1 : 0);
});
