-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项匹配', '2000', '1', 'match', 'ctag/match/index', 1, 'C', '0', '0', 'ctag:match:list', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '特征项匹配菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项匹配查询', @parentId, '1',  '#', '', 1,  'F', '0',  '0', 'ctag:match:query',        '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项匹配新增', @parentId, '2',  '#', '', 1,  'F', '0',  '0', 'ctag:match:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项匹配修改', @parentId, '3',  '#', '', 1,  'F', '0',  '0', 'ctag:match:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项匹配删除', @parentId, '4',  '#', '', 1,  'F', '0',  '0', 'ctag:match:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项匹配导出', @parentId, '5',  '#', '', 1,  'F', '0',  '0', 'ctag:match:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
